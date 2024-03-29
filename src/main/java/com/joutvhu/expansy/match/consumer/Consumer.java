package com.joutvhu.expansy.match.consumer;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.NodeImpl;
import com.joutvhu.expansy.exception.ExpansyException;
import com.joutvhu.expansy.io.Source;
import com.joutvhu.expansy.parser.ExpansyState;
import com.joutvhu.expansy.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class Consumer<E> {
    protected final Source source;
    protected final int offset;
    protected final ExpansyState<E> state;
    protected TrackPoints<E> trackPoints;
    protected List<TrackPoints<E>> cases;
    protected Branch<E> branch;
    protected StopPoint point;

    public Consumer(ExpansyState<E> state) {
        this(state, 0);
    }

    public Consumer(ExpansyState<E> state, int offset) {
        this(state, offset, null);
    }

    public Consumer(ExpansyState<E> state, int offset, Branch<E> branch) {
        this.state = state;
        this.source = state.getSource();
        this.offset = offset;
        this.branch = branch;
        this.point = new StopPoint("", offset);
        this.trackPoints = new TrackPoints<>();
        this.cases = new ArrayList<>();
    }

    public Branch<E> branch() {
        return branch;
    }

    public int offset() {
        return offset;
    }

    public ExpansyState<E> state() {
        return state;
    }

    public Deque<TrackPoint<E>> trackPoints() {
        return trackPoints;
    }

    public StopPoint current() {
        return point;
    }

    public StopPoint next() {
        return next(1);
    }

    public StopPoint next(int length) {
        if (length == 0)
            return point;
        if (length < 0)
            throw new ExpansyException("The length must be greater than 0.");
        String value = source.read(point != null ? point.getIndex() : offset, length);
        if (StringUtils.isNotEmpty(value) && value.length() == length) {
            point = point != null ? point.next(value) : new StopPoint(value, offset);
            return point;
        }
        return null;
    }

    public StopPoint tryNext(int length) {
        if (length == 0)
            return point;
        if (length < 0)
            throw new ExpansyException("The length must be greater than 0.");
        String value = source.read(point != null ? point.getIndex() : offset, length);
        if (value != null) {
            point = point != null ? point.next(value) : new StopPoint(value, offset);
        }
        return point;
    }

    private TrackPoint<E> at(Integer index) {
        if (index == null)
            return new TrackPoint<>(point.getIndex(), point.getValue());
        String value = point.getValue();
        int end = index;
        int next = end - value.length();
        if (next > 0) {
            String v = source.read(point.getIndex(), next);
            if (StringUtils.isNotEmpty(v))
                value = value.concat(v);
        }
        return new TrackPoint<>(offset + index, value.substring(0, end));
    }

    public boolean matched() {
        return !trackPoints.isEmpty();
    }

    public int size() {
        return trackPoints.size();
    }

    public int branchesSize() {
        return cases.size();
    }

    public void removeBefore(int position) {
        final Iterator<TrackPoint<E>> each = trackPoints.iterator();
        while (each.hasNext()) {
            each.next();
            if (position > 0)
                each.remove();
            else
                break;
            position--;
        }
    }

    public void remove(int position) {
        trackPoints.remove(position);
    }

    public void removeAfter(int position) {
        final Iterator<TrackPoint<E>> each = trackPoints.iterator();
        while (each.hasNext()) {
            each.next();
            if (position < 0)
                each.remove();
            position--;
        }
    }

    public void fork() {
        if (!trackPoints.isEmpty()) {
            cases.add(trackPoints);
            trackPoints = new TrackPoints<>();
        }
    }

    public void setCases(List<TrackPoints<E>> cases) {
        this.cases = cases;
    }

    List<TrackPoints<E>> cases() {
        if (!trackPoints.isEmpty())
            cases.add(trackPoints);
        return cases;
    }

    private void forEach(java.util.function.Consumer<TrackPoints<E>> action) {
        cases.forEach(action);
        action.accept(trackPoints);
    }

    private void addForAll(List<NodeImpl<E>> nodes, Integer position) {
        List<TrackPoints<E>> values = new ArrayList<>();
        for (NodeImpl<E> node : nodes) {
            List<TrackPoints<E>> cloneCases = cloneCases();
            for (TrackPoints<E> tp : cloneCases) {
                if (position == null)
                    tp.add(new TrackPoint<>(node));
                else
                    tp.add(position, new TrackPoint<>(node));
            }
            values.addAll(cloneCases);
        }
        this.cases = values;
    }

    private List<TrackPoints<E>> cloneCases() {
        List<TrackPoints<E>> values = new ArrayList<>(this.cases);
        if (!trackPoints.isEmpty())
            values.add(trackPoints);
        if (values.isEmpty())
            values.add(new TrackPoints<>());
        return values;
    }

    /**
     * Remove all other track points, and add a new track point.
     */
    public void only() {
        trackPoints.clear();
        trackPoints.push(at(null));
    }

    public void only(int index) {
        trackPoints.clear();
        trackPoints.push(at(index));
    }

    public void only(NodeImpl<E> node) {
        trackPoints.clear();
        trackPoints.push(new TrackPoint<>(node));
    }

    public void onlyForAll() {
        forEach(value -> {
            value.clear();
            value.push(at(null));
        });
    }

    public void onlyForAll(int index) {
        forEach(value -> {
            value.clear();
            value.push(at(index));
        });
    }

    public void onlyForAll(NodeImpl<E> node) {
        forEach(value -> {
            value.clear();
            value.push(new TrackPoint<>(node));
        });
    }

    /**
     * Add a new track point at the end of the list.
     */
    public void add() {
        trackPoints.add(at(null));
    }

    public void add(int index) {
        trackPoints.add(at(index));
    }

    public void add(NodeImpl<E> node) {
        trackPoints.add(new TrackPoint<>(node));
    }

    public void addForAll() {
        forEach(value -> value.add(at(null)));
    }

    public void addForAll(int index) {
        forEach(value -> value.add(at(index)));
    }

    public void addForAll(NodeImpl<E> node) {
        forEach(value -> value.add(new TrackPoint<>(node)));
    }

    public void addForAll(List<NodeImpl<E>> nodes) {
        addForAll(nodes, null);
    }

    /**
     * Add a new track point to the specified position in the list.
     */
    public void insert(int position) {
        trackPoints.add(position, at(null));
    }

    public void insert(int position, int index) {
        trackPoints.add(position, at(index));
    }

    public void insert(int position, NodeImpl<E> node) {
        trackPoints.add(position, new TrackPoint<>(node));
    }

    public void insertForAll(int position) {
        forEach(value -> value.add(position, at(null)));
    }

    public void insertForAll(int position, int index) {
        forEach(value -> value.add(position, at(index)));
    }

    public void insertForAll(int position, NodeImpl<E> node) {
        forEach(value -> value.add(position, new TrackPoint<>(node)));
    }

    public void insertForAll(int position, List<NodeImpl<E>> nodes) {
        addForAll(nodes, position);
    }

    /**
     * Add a new track point at the first of the list.
     */
    public void push() {
        trackPoints.push(at(null));
    }

    public void push(int index) {
        trackPoints.push(at(index));
    }

    public void push(NodeImpl<E> node) {
        trackPoints.push(new TrackPoint<>(node));
    }

    public void pushForAll() {
        forEach(value -> value.push(at(null)));
    }

    public void pushForAll(int index) {
        forEach(value -> value.push(at(index)));
    }

    public void pushForAll(NodeImpl<E> node) {
        forEach(value -> value.push(new TrackPoint<>(node)));
    }

    public void pushForAll(List<NodeImpl<E>> nodes) {
        addForAll(nodes, 0);
    }

    public void close() {
        throw new StopReasonThrowable(cases(), null, point.getIndex(), point.getValue());
    }

    public void complete() {
        this.push();
        this.close();
    }

    public void error(String pattern, Object... arguments) {
        String message = arguments.length == 0 ? pattern : MessageFormat.format(pattern, arguments);
        throw new StopReasonThrowable(cases(), message, point.getIndex(), point.getValue());
    }

    public void errorAt(String pattern, Integer index, String content, Object... arguments) {
        String message = arguments.length == 0 ? pattern : MessageFormat.format(pattern, arguments);
        throw new StopReasonThrowable(cases(), message, index, content);
    }
}
