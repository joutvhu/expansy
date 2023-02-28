package com.joutvhu.expansy.match.consumer;

import java.util.Deque;

final class StopReasonThrowable extends RuntimeException {
    private Integer position;
    private String content;
    private Deque<?> trackPoints;

    StopReasonThrowable(Deque<?> trackPoints) {
        this.trackPoints = trackPoints;
    }

    StopReasonThrowable(Deque<?> trackPoints, String message, Integer position, String content) {
        super(message);
        this.trackPoints = trackPoints;
        this.position = position;
        this.content = content;
    }

    StopReason reason() {
        return new StopReason(trackPoints, getMessage(), position, content);
    }
}
