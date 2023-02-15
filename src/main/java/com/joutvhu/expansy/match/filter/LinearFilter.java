package com.joutvhu.expansy.match.filter;

import com.joutvhu.expansy.parser.StringSource;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class LinearFilter {
    private final StringSource source;
    private final Deque<Integer> trackPoints;
    private StopPoint point;

    public LinearFilter(StringSource source) {
        this.source = source;
        this.trackPoints = new ArrayDeque<>();
    }

    public StopPoint next() {
        return next(1);
    }

    public StopPoint next(int length) {
        String value = source.read(length);
        if (StringUtils.isNotEmpty(value) && value.length() == length) {
            point = point != null ? point.next(value) : new StopPoint(value);
            return point;
        }
        return null;
    }

    public void push() {
        trackPoints.push(point.getIndex());
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
