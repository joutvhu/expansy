package com.joutvhu.dynamic.expression.analysis.match.filter;

import com.joutvhu.dynamic.expression.analysis.parser.StringSource;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class LinearFilter {
    private final StringSource stringSource;
    private final Deque<Integer> trackPoints;
    private StopPoint point;

    public LinearFilter(StringSource stringSource) {
        this.stringSource = stringSource;
        this.trackPoints = new ArrayDeque<>();
    }

    public StopPoint next() {
        return next(1);
    }

    public StopPoint next(int length) {
        String value = stringSource.read(length);
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
