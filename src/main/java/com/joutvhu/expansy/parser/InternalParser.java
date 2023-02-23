package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.exception.ExpansyException;
import com.joutvhu.expansy.exception.MatchException;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.StopReason;
import com.joutvhu.expansy.match.consumer.TrackPoint;
import com.joutvhu.expansy.match.definer.DefinerUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class InternalParser<E> {
    private ExpansyState<E> state;

    public InternalParser(ExpansyState<E> state) {
        this.state = state;
        this.state.setParser(this);
    }

    public List<Branch<E>> parseByElements(Collection<Element<E>> elements) {
        return parseByElements(elements, 0, new Branch<>());
    }

    public List<Branch<E>> parseByElements(Collection<Element<E>> elements, int offset, Branch<E> branch) {
        List<Branch<E>> branches = new ArrayList<>();
        ExpansyException error = null;
        for (Element<E> element : elements) {
            if (branch == null || !branch.started(offset, element)) {
                try {
                    if (branch != null)
                        branch.start(offset, element);
                    Params<E> params = parseByElement(element, offset, branch);
                    if (params.getLength() > 0) {
                        Branch<E> newBranch = branch.clone();
                        newBranch.add(params);
                        try {
                            if (state.getLength() <= params.getEnd()) {
                                branches.add(newBranch);
                            } else {
                                List<Branch<E>> values = parseByElements(elements, params.getEnd(), newBranch);
                                branches.addAll(values);
                            }
                        } catch (Exception e) {
                            error = ExpansyException.of(e);
                        }
                    }
                } catch (Exception e) {
                    if (error == null)
                        error = ExpansyException.of(e);
                } finally {
                    if (branch != null)
                        branch.complete(offset, element);
                }
            }
        }
        if (branches.isEmpty() && error != null)
            throw error;
        return branches;
    }

    public List<Params<E>> parseByElements(Collection<Element<E>> elements, Consumer<E> consumer) {
        List<Params<E>> results = new ArrayList<>();
        ExpansyException error = null;
        Branch<E> branch = consumer.branch();
        if (consumer.offset() < state.getLength()) {
            for (Element<E> element : elements) {
                if (branch == null || !branch.started(consumer.offset(), element)) {
                    try {
                        if (branch != null)
                            branch.start(consumer.offset(), element);
                        Params<E> params = parseByElement(element, consumer.offset(), consumer.branch());
                        if (params.getLength() > 0)
                            results.add(params);
                    } catch (Exception e) {
                        if (error == null)
                            error = ExpansyException.of(e);
                    } finally {
                        if (branch != null)
                            branch.complete(consumer.offset(), element);
                    }
                }
            }
            results.sort(Comparator.comparingInt(Params::getLength));
        }
        return results;
    }

    public Params<E> parseByElement(Element<E> element, Integer offset, Branch<E> branch) {
        List<Matcher<E>> matchers = DefinerUtil.matchersOf(element);
        Params<E> params = parseByMatchers(matchers, offset, branch);
        params.setElement(element);
        return params;
    }

    public Params<E> parseByMatchers(Collection<Matcher<E>> matchers, Consumer<E> consumer) {
        return parseByMatchers(matchers, consumer.offset(), consumer.branch());
    }

    public Params<E> parseByMatchers(Collection<Matcher<E>> matchers, Integer offset, Branch<E> branch) {
        Params<E> params = new Params<>();
        params.setStart(offset != null ? offset : 0);
        params.setEnd(params.getStart());
        Consumer<E> consumer = new Consumer<>(state, params.getStart(), branch);
        Deque<CheckNode<E>> nodes = new ArrayDeque<>();
        Matcher<E>[] array = matchers.toArray(new Matcher[0]);
        for (int i = 0, len = matchers.size(); i < len; i++) {
            Matcher<E> matcher = array[i];
            try {
                matcher.match(consumer);
                consumer.close();
            } catch (StopReason reason) {
                Deque<TrackPoint> trackPoints = reason.getTrackPoints();
                TrackPoint trackPoint = trackPoints.isEmpty() ? null : trackPoints.pop();
                while (trackPoint == null && !nodes.isEmpty()) {
                    // Back to other track point.
                    CheckNode<E> node = nodes.pop();
                    trackPoints = node.trackPoints;
                    trackPoint = trackPoints.pop();
                    i = nodes.size();
                }

                if (trackPoint != null) {
                    CheckNode<E> node = new CheckNode<>(matcher, trackPoint, trackPoints);
                    nodes.push(node);
                    consumer = new Consumer<>(state, trackPoint.getIndex(), branch);
                } else if (i == 0) {
                    if (StringUtils.isBlank(reason.getMessage())) {
                        if (trackPoints.isEmpty())
                            throw new MatchException("No track point found.");
                    }
                    throw new MatchException(reason.getMessage());
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (CheckNode<E> node = nodes.pollLast(); node != null; node = nodes.pollLast()) {
            Matcher<E> matcher = node.matcher;
            Params<E> p = node.point.getParams();
            if (matcher.getName() != null) {
                if (p != null)
                    params.add(matcher.getName(), p);
                params.add(matcher.getName(), node.point.getValue());
            }
            if (p != null) {
                if (p.getStart() < params.getStart())
                    params.setStart(p.getStart());
                if (params.getEnd() < p.getEnd())
                    params.setEnd(p.getEnd());
            }
            if (params.getEnd() < node.point.getIndex())
                params.setEnd(node.point.getIndex());
            // Join all value of nodes
            builder.append(node.point.getValue());
        }
        params.setValue(builder.toString());

        return params;
    }

    @AllArgsConstructor
    private class CheckNode<E> {
        private Matcher<E> matcher;
        private TrackPoint point;
        private Deque<TrackPoint> trackPoints;
    }
}
