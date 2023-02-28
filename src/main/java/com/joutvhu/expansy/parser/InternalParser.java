package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Node;
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
        MatchException error = null;
        for (Element<E> element : elements) {
            if (branch == null || !branch.started(offset, element)) {
                try {
                    if (branch != null)
                        branch.start(offset, element);
                    Node<E> node = parseByElement(element, offset, branch);
                    if (!node.isEmpty()) {
                        Branch<E> newBranch = branch.clone();
                        newBranch.add(node);
                        try {
                            if (state.getLength() <= node.getEnd()) {
                                branches.add(newBranch);
                            } else {
                                List<Branch<E>> values = parseByElements(elements, node.getEnd(), newBranch);
                                branches.addAll(values);
                            }
                        } catch (Exception e) {
                            error = MatchException.or(error, MatchException.of(e));
                        }
                    }
                } catch (Exception e) {
                    error = MatchException.or(error, MatchException.of(e));
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

    public List<Node<E>> parseByElements(Collection<Element<E>> elements, Consumer<E> consumer) {
        List<Node<E>> results = new ArrayList<>();
        MatchException error = null;
        Branch<E> branch = consumer.branch();
        if (consumer.offset() < state.getLength()) {
            for (Element<E> element : elements) {
                if (branch == null || !branch.started(consumer.offset(), element)) {
                    try {
                        if (branch != null)
                            branch.start(consumer.offset(), element);
                        Node<E> node = parseByElement(element, consumer.offset(), consumer.branch());
                        if (node.getLength() > 0)
                            results.add(node);
                    } catch (Exception e) {
                        error = MatchException.or(error, MatchException.of(e));
                    } finally {
                        if (branch != null)
                            branch.complete(consumer.offset(), element);
                    }
                }
            }
        }
        if (results.isEmpty() && error != null)
            throw error;
        results.sort(Comparator.comparingInt(Node::getLength));
        return results;
    }

    public Node<E> parseByElement(Element<E> element, Integer offset, Branch<E> branch) {
        List<Matcher<E>> matchers = DefinerUtil.matchersOf(element);
        Node<E> node = parseByMatchers(matchers, offset, branch);
        node.setElement(element);
        return node;
    }

    public Node<E> parseByMatchers(Collection<Matcher<E>> matchers, Consumer<E> consumer) {
        return parseByMatchers(matchers, consumer.offset(), consumer.branch());
    }

    public Node<E> parseByMatchers(Collection<Matcher<E>> matchers, Integer offset, Branch<E> branch) {
        Node<E> result = new Node<>();
        result.setStart(offset != null ? offset : 0);
        result.setEnd(result.getStart());
        Consumer<E> consumer = new Consumer<>(state, result.getStart(), branch);
        Deque<CheckNode<E>> nodes = new ArrayDeque<>();
        Matcher<E>[] array = matchers.toArray(new Matcher[0]);
        StopReason<E> error = null;
        for (int i = 0, len = matchers.size(); i < len; i++) {
            Matcher<E> matcher = array[i];
            StopReason<E> reason = StopReason.of(matcher, consumer);

            if (!reason.isSuccess()) {
                if (error == null || error.getPosition() == null ||
                        (reason.getPosition() != null && error.getPosition() < reason.getPosition()))
                    error = reason;
            }

            Deque<TrackPoint<E>> trackPoints = reason.getTrackPoints();
            TrackPoint<E> trackPoint = trackPoints.isEmpty() ? null : trackPoints.pop();
            while (trackPoint == null && !nodes.isEmpty()) {
                // Back to other track point.
                CheckNode<E> node = nodes.pop();
                trackPoints = node.trackPoints;
                trackPoint = trackPoints.isEmpty() ? null : trackPoints.pop();
                i = nodes.size();
            }

            if (trackPoint != null) {
                CheckNode<E> node = new CheckNode<>(matcher, trackPoint, trackPoints);
                nodes.push(node);
                consumer = new Consumer<>(state, trackPoint.getIndex(), branch);
            } else if (i == 0) {
                if (error != null)
                    throw new MatchException(error.getMessage(), error.getPosition(), error.getContent());
                if (StringUtils.isBlank(reason.getMessage())) {
                    if (trackPoints.isEmpty())
                        throw new MatchException("No track point found.");
                }
                throw new MatchException(reason.getMessage(), reason.getPosition(), reason.getContent());
            }
        }

        StringBuilder builder = new StringBuilder();
        for (CheckNode<E> node = nodes.pollLast(); node != null; node = nodes.pollLast()) {
            Matcher<E> matcher = node.matcher;
            Node<E> p = node.point.getNode();
            if (matcher.getName() != null) {
                if (p != null)
                    result.add(matcher.getName(), p);
                result.add(matcher.getName(), node.point.getValue());
            }
            if (p != null) {
                result.addAll(p);
                if (p.getStart() < result.getStart())
                    result.setStart(p.getStart());
                if (result.getEnd() < p.getEnd())
                    result.setEnd(p.getEnd());
            }
            if (result.getEnd() < node.point.getIndex())
                result.setEnd(node.point.getIndex());
            // Join all value of nodes
            builder.append(node.point.getValue());
        }
        result.setValue(builder.toString());

        return result;
    }

    @AllArgsConstructor
    private class CheckNode<E> {
        private Matcher<E> matcher;
        private TrackPoint<E> point;
        private Deque<TrackPoint<E>> trackPoints;
    }
}
