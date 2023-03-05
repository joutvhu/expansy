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
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class ExpansyAnalyser<E> implements Analyser<E> {
    private ExpansyState<E> state;

    ExpansyAnalyser(ExpansyState<E> state) {
        this.state = state;
    }

    @Override
    public List<Branch<E>> analyse(Collection<Element<E>> elements) {
        return analyse(elements, 0, new Branch<>());
    }

    @Override
    public List<Branch<E>> analyse(Collection<Element<E>> elements, Consumer<E> consumer) {
        return analyse(elements, consumer.offset(), consumer.branch());
    }

    @Override
    public List<Branch<E>> analyse(Collection<Element<E>> elements, Integer offset, Branch<E> branch) {
        List<Branch<E>> branches = new ArrayList<>();
        MatchException error = null;
        for (Element<E> element : elements) {
            if (branch == null || !branch.started(offset, element)) {
                try {
                    if (branch != null)
                        branch.start(offset, element);
                    Node<E> node = analyseElement(element, offset, branch);
                    if (!node.isEmpty()) {
                        Branch<E> newBranch = branch.clone();
                        newBranch.push(node);
                        try {
                            if (state.getLength() <= node.getEnd()) {
                                branches.add(newBranch);
                            } else {
                                List<Branch<E>> values = analyse(elements, node.getEnd(), newBranch);
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

    @Override
    public List<Node<E>> analyseElements(Collection<Element<E>> elements, Consumer<E> consumer) {
        return analyseElements(elements, consumer.offset(), consumer.branch());
    }

    @Override
    public List<Node<E>> analyseElements(Collection<Element<E>> elements, Integer offset, Branch<E> branch) {
        List<Node<E>> results = new ArrayList<>();
        MatchException error = null;
        if (offset < state.getLength()) {
            for (Element<E> element : elements) {
                if (branch == null || !branch.started(offset, element)) {
                    try {
                        if (branch != null)
                            branch.start(offset, element);
                        Node<E> node = analyseElement(element, offset, branch);
                        if (node.getLength() > 0)
                            results.add(node);
                    } catch (Exception e) {
                        error = MatchException.or(error, MatchException.of(e));
                    } finally {
                        if (branch != null)
                            branch.complete(offset, element);
                    }
                }
            }
        }
        if (results.isEmpty() && error != null)
            throw error;
        results.sort(Comparator.comparingInt(Node::getLength));
        return results;
    }

    @Override
    public Node<E> analyseElement(Element<E> element, Consumer<E> consumer) {
        return analyseElement(element, consumer.offset(), consumer.branch());
    }

    @Override
    public Node<E> analyseElement(Element<E> element, Integer offset, Branch<E> branch) {
        List<Matcher<E>> matchers = DefinerUtil.matchersOf(element);
        Node<E> node = analyseMatchers(matchers, offset, branch);
        node.setElement(element);
        return node;
    }

    @Override
    public Node<E> analyseMatchers(Collection<Matcher<E>> matchers, Consumer<E> consumer) {
        return analyseMatchers(matchers, consumer.offset(), consumer.branch());
    }

    @Override
    public Node<E> analyseMatchers(Collection<Matcher<E>> matchers, Integer offset, Branch<E> branch) {
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

            if (error == null || error.getPosition() == null ||
                    (reason.getPosition() != null && error.getPosition() < reason.getPosition())) {
                error = reason;
            }

            CheckNode<E> node = new CheckNode<>(matcher, reason.getTrackPoints());
            TrackPoint<E> trackPoint = node.getPoint();
            while (trackPoint == null && !nodes.isEmpty()) {
                // Back to other track point.
                node = nodes.pop();
                trackPoint = node.pop();
                i = nodes.size();
            }

            if (trackPoint != null) {
                nodes.push(node);
                consumer = new Consumer<>(state, trackPoint.getIndex(), branch);
            } else if (i == 0) {
                String message = StringUtils.isNotBlank(error.getMessage()) ? error.getMessage() :
                        MessageFormat.format("Unable to parse \"{0}\".", error.getContent());
                throw new MatchException(message, error.getPosition(), error.getContent());
            }
        }

        StringBuilder builder = new StringBuilder();
        for (CheckNode<E> node = nodes.pollLast(); node != null; node = nodes.pollLast()) {
            Matcher<E> matcher = node.getMatcher();
            Node<E> p = node.getPoint().getNode();
            if (matcher.getName() != null) {
                if (p != null)
                    result.add(matcher.getName(), p);
                result.add(matcher.getName(), node.getPoint().getValue());
            }
            if (p != null) {
                if (matcher.getName() == null)
                    result.addAll(p);
                if (p.getStart() < result.getStart())
                    result.setStart(p.getStart());
                if (result.getEnd() < p.getEnd())
                    result.setEnd(p.getEnd());
            }
            if (result.getEnd() < node.getPoint().getIndex())
                result.setEnd(node.getPoint().getIndex());
            // Join all value of nodes
            builder.append(node.getPoint().getValue());
        }
        result.setValue(builder.toString());

        return result;
    }
}
