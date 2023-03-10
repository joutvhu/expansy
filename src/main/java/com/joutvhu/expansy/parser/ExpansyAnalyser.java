package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.exception.ExpansyException;
import com.joutvhu.expansy.exception.MatchException;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.StopReason;
import com.joutvhu.expansy.match.consumer.TrackPoint;
import com.joutvhu.expansy.match.consumer.TrackPoints;
import com.joutvhu.expansy.match.definer.DefinerUtil;
import com.joutvhu.expansy.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class ExpansyAnalyser<E> implements Analyser<E> {
    private final ExpansyState<E> state;

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
        ExpansyException error = null;
        for (Element<E> element : elements) {
            if (branch == null || !branch.started(offset, element)) {
                try {
                    if (branch != null)
                        branch.start(offset, element);
                    List<Node<E>> nodes = analyseElement(element, offset, branch);
                    for (Node<E> node : nodes) {
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
                                error = ExpansyException.or(error, e);
                            }
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
        return analyseElements(elements, consumer.offset(), consumer.branch(), null);
    }

    private List<Node<E>> analyseElements(Collection<Element<E>> elements, Integer offset, Branch<E> branch, Node<E> node) {
        List<Node<E>> result = new ArrayList<>();
        Branch<E> newBranch;
        if (node != null) {
            offset = node.getEnd();
            result.add(node);
            newBranch = branch.clone();
            newBranch.push(node);
        } else {
            newBranch = branch;
        }
        List<Node<E>> nodes = analyseElements(elements, offset, newBranch);
        for (Node<E> eNode : nodes) {
            if (node == null || eNode.getStart() < node.getEnd()) {
                try {
                    List<Node<E>> children = analyseElements(elements, eNode.getEnd(), branch, eNode);
                    result.addAll(children);
                } catch (Exception e) {
                    if (node == null) result.add(eNode);
                }
            }
        }
        return result;
    }

    @Override
    public List<Node<E>> analyseElements(Collection<Element<E>> elements, Integer offset, Branch<E> branch) {
        List<Node<E>> results = new ArrayList<>();
        ExpansyException error = null;
        if (offset < state.getLength()) {
            for (Element<E> element : elements) {
                if (branch == null || !branch.started(offset, element)) {
                    try {
                        if (branch != null)
                            branch.start(offset, element);
                        List<Node<E>> nodes = analyseElement(element, offset, branch);
                        nodes.forEach(node -> {
                            if (!node.isEmpty())
                                results.add(node);
                        });
                    } catch (Exception e) {
                        error = ExpansyException.or(error, e);
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
    public List<Node<E>> analyseElement(Element<E> element, Consumer<E> consumer) {
        return analyseElement(element, consumer.offset(), consumer.branch());
    }

    @Override
    public List<Node<E>> analyseElement(Element<E> element, Integer offset, Branch<E> branch) {
        List<Matcher<E>> matchers = DefinerUtil.matchersOf(element);
        List<Node<E>> node = analyseMatchers(matchers, offset, branch);
        node.forEach(value -> value.setElement(element));
        return node;
    }

    @Override
    public List<Node<E>> analyseMatchers(Collection<Matcher<E>> matchers, Consumer<E> consumer) {
        return analyseMatchers(matchers, consumer.offset(), consumer.branch());
    }

    @Override
    public List<Node<E>> analyseMatchers(Collection<Matcher<E>> matchers, Integer offset, Branch<E> branch) {
        Matcher<E>[] array = matchers.toArray(new Matcher[0]);
        return analyseMatchers(array, offset, branch);
    }

    private List<Node<E>> analyseMatchers(Matcher<E>[] matchers, Integer offset, Branch<E> branch) {
        Node<E> node = new Node<>(state);
        node.setStart(offset != null ? offset : 0);
        node.setEnd(node.getStart());
        return analyseMatchers(matchers, 0, offset, null, branch, node);
    }

    @Override
    public List<Node<E>> analyseMatchers(Collection<Matcher<E>> matchers, List<Node<E>> nodes, Branch<E> branch) {
        Matcher<E>[] array = matchers.toArray(new Matcher[0]);
        return analyseMatchers(array, nodes, branch);
    }

    @Override
    public List<Node<E>> analyseMatchers(Collection<Matcher<E>> matchers, Branch<E> branch, List<TrackPoints<E>> cases) {
        Matcher<E>[] array = matchers.toArray(new Matcher[0]);
        List<Node<E>> result = new ArrayList<>();
        ExpansyException error = null;
        for (TrackPoints<E> trackPoints : cases) {
            Node<E> node = trackPoints.lastNode();
            if (node != null) {
                try {
                    Node<E> cloneNode = node.clone();
                    List<Node<E>> children = analyseMatchers(array, 0, cloneNode.getEnd(), null, branch, cloneNode);
                    children.forEach(eNode -> eNode.setTrackPoints(trackPoints));
                    result.addAll(children);
                } catch (Exception e) {
                    error = ExpansyException.or(error, e);
                }
            }
        }
        if (result.isEmpty() && error != null)
            throw error;
        return result;
    }

    private List<Node<E>> analyseMatchers(Matcher<E>[] matchers, List<Node<E>> nodes, Branch<E> branch) {
        List<Node<E>> result = new ArrayList<>();
        ExpansyException error = null;
        for (Node<E> node : nodes) {
            try {
                Node<E> cloneNode = node.clone();
                List<Node<E>> children = analyseMatchers(matchers, 0, cloneNode.getEnd(), null, branch, cloneNode);
                TrackPoints<E> trackPoints = cloneNode.getTrackPoints();
                if (trackPoints != null)
                    children.forEach(eNode -> eNode.setTrackPoints(trackPoints));
                result.addAll(children);
            } catch (Exception e) {
                error = ExpansyException.or(error, e);
            }
        }
        if (result.isEmpty() && error != null)
            throw error;
        return result;
    }

    @Override
    public List<Node<E>> analyseMatchers(Collection<Matcher<E>> matchers, Node<E> node, Branch<E> branch) {
        Matcher<E>[] array = matchers.toArray(new Matcher[0]);
        List<Node<E>> result = analyseMatchers(array, node, branch);
        TrackPoints<E> trackPoints = node.getTrackPoints();
        if (trackPoints != null)
            result.forEach(eNode -> eNode.setTrackPoints(trackPoints));
        return result;
    }

    @Override
    public List<Node<E>> analyseMatchers(Collection<Matcher<E>> matchers, Branch<E> branch, TrackPoints<E> trackPoints) {
        Matcher<E>[] array = matchers.toArray(new Matcher[0]);
        Node<E> node = trackPoints.lastNode();
        if (node == null)
            return new ArrayList<>();
        List<Node<E>> result = analyseMatchers(array, node, branch);
        result.forEach(eNode -> eNode.setTrackPoints(trackPoints));
        return result;
    }

    @Override
    public List<Node<E>> analyseMatchers(Collection<Matcher<E>> matchers, Branch<E> branch, TrackPoint<E> trackPoint) {
        Matcher<E>[] array = matchers.toArray(new Matcher[0]);
        Node<E> node = trackPoint.getNode();
        if (node == null) return new ArrayList<>();
        return analyseMatchers(array, node, branch);
    }

    private List<Node<E>> analyseMatchers(Matcher<E>[] matchers, Node<E> node, Branch<E> branch) {
        return analyseMatchers(matchers, 0, node.getEnd(), null, branch, node.clone());
    }

    private List<Node<E>> analyseMatchers(Matcher<E>[] matchers, int start, List<TrackPoints<E>> cases, Branch<E> branch, Node<E> node) {
        List<Node<E>> result = new ArrayList<>();
        ExpansyException error = null;
        for (TrackPoints<E> trackPoints : cases) {
            try {
                List<Node<E>> nodes = analyseMatchers(matchers, start, null, trackPoints, branch, node.clone());
                if (nodes != null) result.addAll(nodes);
            } catch (Exception e) {
                error = ExpansyException.or(error, e);
            }
        }
        if (result.isEmpty() && error != null)
            throw error;
        return result;
    }

    private List<Node<E>> analyseMatchers(Matcher<E>[] matchers, Integer start, Integer offset, TrackPoints<E> trackPoints, Branch<E> branch, Node<E> node) {
        Deque<TrackPoints<E>> nodes = new ArrayDeque<>();
        if (trackPoints != null) {
            nodes.push(trackPoints);
            TrackPoint<E> point = trackPoints.next();
            offset = point.getIndex();
        }
        Consumer<E> consumer = new Consumer<>(state, offset, branch);
        final int len = matchers.length;
        StopReason<E> errorReason = null;
        ExpansyException error = null;
        for (int i = start + nodes.size(); i < len; i = start + nodes.size()) {
            Matcher<E> matcher = matchers[i];
            StopReason<E> reason = StopReason.of(matcher, consumer);

            if (reason.isSuccess()) {
                if (reason.isSingle()) {
                    consumer = newConsumer(nodes, reason, branch);
                } else {
                    try {
                        return analyseMatchers(matchers, i, reason.getCases(), branch, mergeNode(node, nodes));
                    } catch (Exception e) {
                        if (nodes.isEmpty()) throw e;
                        consumer = newConsumer(nodes, branch);
                        error = ExpansyException.or(error, e);
                        if (consumer == null) {
                            throw error;
                        }
                    }
                }
            } else {
                consumer = newConsumer(nodes, branch);
                StopReason<E> newErrorReason = StopReason.or(errorReason, reason);
                if (newErrorReason != errorReason) {
                    errorReason = newErrorReason;
                    if (error == null || !(error instanceof MatchException) ||
                            ((MatchException) error).getIndex() < errorReason.getPosition()) {
                        String message = StringUtils.isNotBlank(errorReason.getMessage()) ? errorReason.getMessage() :
                                MessageFormat.format("Unable to parse \"{0}\".", errorReason.getContent());
                        error = new MatchException(message, errorReason.getPosition(), errorReason.getContent());
                    }
                }
                if (consumer == null) {
                    throw error;
                }
            }
        }
        if (nodes.isEmpty() && error != null)
            throw error;
        return Arrays.asList(mergeNode(node, nodes));
    }

    private Consumer<E> newConsumer(Deque<TrackPoints<E>> nodes, StopReason<E> reason, Branch<E> branch) {
        TrackPoint<E> point = selectPoint(nodes, reason);
        return new Consumer<>(state, point.getIndex(), branch);
    }

    private Consumer<E> newConsumer(Deque<TrackPoints<E>> nodes, Branch<E> branch) {
        TrackPoint<E> point = selectPoint(nodes);
        return point != null ? new Consumer<>(state, point.getIndex(), branch) : null;
    }

    private TrackPoint<E> selectPoint(Deque<TrackPoints<E>> nodes) {
        while (!nodes.isEmpty()) {
            TrackPoints<E> trackPoints = nodes.getFirst();
            TrackPoint<E> point = trackPoints.next();
            if (point != null) {
                return point;
            } else {
                nodes.removeFirst();
            }
        }
        return null;
    }

    private TrackPoint<E> selectPoint(Deque<TrackPoints<E>> nodes, StopReason<E> reason) {
        List<TrackPoints<E>> cases = reason.getCases();
        if (cases.isEmpty())
            throw new MatchException(reason.getMessage(), reason.getPosition(), reason.getContent());
        TrackPoints<E> trackPoints = cases.get(0);
        nodes.push(trackPoints);
        TrackPoint<E> point = selectPoint(nodes);
        if (point != null) {
            return point;
        } else {
            throw new MatchException(reason.getMessage(), reason.getPosition(), reason.getContent());
        }
    }

    private Node<E> mergeNode(Node<E> node, Deque<TrackPoints<E>> nodes) {
        StringBuilder builder = new StringBuilder();
        if (node.getValue() != null)
            builder.append(node.getValue());
        for (TrackPoints<E> trackPoints = nodes.pollLast(); trackPoints != null; trackPoints = nodes.pollLast()) {
            Matcher<E> matcher = trackPoints.getMatcher();
            TrackPoint<E> trackPoint = trackPoints.trackPoint();
            if (trackPoint != null) {
                Node<E> p = trackPoint.getNode();
                if (matcher.getName() != null) {
                    if (p != null)
                        node.add(matcher.getName(), p);
                    node.add(matcher.getName(), trackPoint.getValue());
                } else if (p != null && p.isEmbed())
                    node.addAll(p);
                if (p != null) {
                    if (p.getStart() < node.getStart())
                        node.setStart(p.getStart());
                    if (node.getEnd() < p.getEnd())
                        node.setEnd(p.getEnd());
                }
                if (node.getEnd() < trackPoint.getIndex())
                    node.setEnd(trackPoint.getIndex());
                // Join all value of nodes
                builder.append(trackPoint.getValue());
            }
        }
        node.setValue(builder.toString());
        return node;
    }
}
