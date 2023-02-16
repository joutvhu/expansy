package com.joutvhu.expansy.match.filter;

import com.joutvhu.expansy.io.Source;
import com.joutvhu.expansy.parser.ExpansyState;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class LinearFilter<E> implements Filter {
    protected final ExpansyState<E> state;
    protected final Source source;
    protected final Deque<TrackPoint> trackPoints;
    protected long offset;
    protected StopPoint point;

    public LinearFilter(ExpansyState<E> state) {
        this(state, 0);
    }

    public LinearFilter(ExpansyState<E> state, long offset) {
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
    public void push() {
        TrackPoint trackPoint = new TrackPoint(point.getIndex(), point.getValue());
        trackPoints.push(trackPoint);
    }

    @Override
    public void push(int index) {
        StopPoint point = this.point;
        int next = index + 1 - point.getLength();
        if (next > 0) {
            String value = source.read( next);
            next(next);
        }
        TrackPoint trackPoint = new TrackPoint(point.getIndex(), point.getValue().substring(0, index + 1));
        trackPoints.push(trackPoint);
    }

    @Override
    public void close() {
        throw new StopReason(0, trackPoints);
    }

    @Override
    public void complete() {
        this.push();
        this.close();
    }

    @Override
    public void error(String message) {
        throw new StopReason(1, trackPoints, message);
    }
}
