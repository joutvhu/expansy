package com.joutvhu.expansy.match.filter;

import com.joutvhu.expansy.io.Source;
import com.joutvhu.expansy.parser.ExpansyState;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class LinearFilter<E> {
    protected final ExpansyState<E> config;
    protected final Source source;
    protected final Deque<TrackPoint> trackPoints;
    protected long offset;
    protected StopPoint point;

    public LinearFilter(ExpansyState<E> config) {
        this(config, 0);
    }

    public LinearFilter(ExpansyState<E> config, long offset) {
        this.config = config;
        this.source = config.getSource();
        this.offset = offset;
        this.trackPoints = new ArrayDeque<>();
    }

    public ExpansyState<E> configuration() {
        return config;
    }

    public StopPoint next() {
        return next(1);
    }

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

    public void push() {
        TrackPoint trackPoint = new TrackPoint(point.getIndex(), point.getValue());
        trackPoints.push(trackPoint);
    }

    public void complete() {
        throw new StopReason(0, trackPoints);
    }

    public void pushAndComplete() {
        this.push();
        this.complete();
    }

    public void error(String message) {
        throw new StopReason(1, trackPoints, message);
    }
}
