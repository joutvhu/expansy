package com.joutvhu.expansy.match.consumer;

import java.util.Deque;

final class StopReasonThrowable extends RuntimeException {
    private final Integer position;
    private final String content;
    private final Deque<? extends TrackPoint<?>> trackPoints;

    StopReasonThrowable(Deque<? extends TrackPoint<?>> trackPoints, String message, Integer position, String content) {
        super(message);
        this.trackPoints = trackPoints;
        this.position = position;
        this.content = content;
    }

    StopReason<?> reason() {
        return new StopReason<>(trackPoints, getMessage(), position, content);
    }
}
