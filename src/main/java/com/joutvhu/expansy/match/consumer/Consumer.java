package com.joutvhu.expansy.match.consumer;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.io.Source;
import com.joutvhu.expansy.parser.ExpansyState;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class Consumer<E> {
    protected final Source source;
    protected final int offset;
    protected final ExpansyState<E> state;
    protected final Deque<TrackPoint> trackPoints;
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
        this.trackPoints = new ArrayDeque<>();
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

    public Deque<TrackPoint> trackPoints() {
        return trackPoints;
    }

    public StopPoint next() {
        return next(1);
    }

    public StopPoint next(int length) {
        if (length == 0)
            return point;
        assert (length > 0);
        String value = source.read(point.getIndex(), length);
        if (StringUtils.isNotEmpty(value) && value.length() == length) {
            point = point != null ? point.next(value) : new StopPoint(value, offset);
            return point;
        }
        return null;
    }

    private TrackPoint at(Integer index) {
        if (index == null)
            return new TrackPoint(point.getIndex(), point.getValue());
        String value = point.getValue();
        int end = index;
        int next = end - value.length();
        if (next > 0) {
            String v = source.read(point.getIndex(), next);
            if (StringUtils.isNotEmpty(v))
                value = value.concat(v);
        }
        return new TrackPoint(offset + index, value.substring(0, end));
    }

    public void select() {
        trackPoints.clear();
        trackPoints.push(at(null));
    }

    public void select(int index) {
        trackPoints.clear();
        trackPoints.push(at(index));
    }

    public void select(Params<E> params) {
        trackPoints.clear();
        trackPoints.push(new TrackPoint(params));
    }

    public void stack() {
        trackPoints.addLast(at(null));
    }

    public void stack(int index) {
        trackPoints.addLast(at(index));
    }

    public void stack(Params<E> params) {
        trackPoints.addLast(new TrackPoint(params));
    }

    public void push() {
        trackPoints.addFirst(at(null));
    }

    public void push(int index) {
        trackPoints.addFirst(at(index));
    }

    public void push(Params<E> params) {
        trackPoints.addFirst(new TrackPoint(params));
    }

    public void close() {
        throw new StopReason(0, trackPoints);
    }

    public void complete() {
        this.stack();
        this.close();
    }

    public void error(String message) {
        throw new StopReason(1, trackPoints, message);
    }
}
