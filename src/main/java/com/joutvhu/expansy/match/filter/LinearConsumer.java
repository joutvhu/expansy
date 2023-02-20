package com.joutvhu.expansy.match.filter;

import com.joutvhu.expansy.io.Source;
import com.joutvhu.expansy.parser.ExpansyState;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class LinearConsumer<E> implements Consumer {
    protected final ExpansyState<E> state;
    protected final Source source;
    protected final Deque<TrackPoint> trackPoints;
    protected long offset;
    protected StopPoint point;

    public LinearConsumer(ExpansyState<E> state) {
        this(state, 0);
    }

    public LinearConsumer(ExpansyState<E> state, long offset) {
        this.state = state;
        this.source = state.getSource();
        this.offset = offset;
        this.trackPoints = new ArrayDeque<>();
    }

    public ExpansyState<E> state() {
        return state;
    }

    public Deque<TrackPoint> trackPoints() {
        return trackPoints;
    }

    @Override
    public StopPoint next() {
        return next(1);
    }

    @Override
    public StopPoint next(int length) {
        if (length == 0)
            return point;
        assert (length > 0);
        String value = source.read(length);
        if (StringUtils.isNotEmpty(value) && value.length() == length) {
            point = point != null ? point.next(value) : new StopPoint(value, offset);
            return point;
        }
        return null;
    }

    @Override
    public void stack() {
        TrackPoint trackPoint = new TrackPoint(point.getIndex(), point.getValue());
        trackPoints.push(trackPoint);
    }

    @Override
    public void stack(int index) {
        String value = this.point.getValue();
        int next = index + 1 - value.length();
        if (next > 0) {
            String v = source.read(next);
            if (StringUtils.isNotEmpty(v))
                value = value.concat(v);
        }
        TrackPoint trackPoint = new TrackPoint(value.length() - 1, value.substring(0, index + 1));
        trackPoints.push(trackPoint);
    }

    @Override
    public void push() {

    }

    @Override
    public void push(int index) {

    }

    @Override
    public void close() {
        throw new StopReason(0, trackPoints);
    }

    @Override
    public void complete() {
        this.stack();
        this.close();
    }

    @Override
    public void error(String message) {
        throw new StopReason(1, trackPoints, message);
    }
}
