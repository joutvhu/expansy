package com.joutvhu.expansy.match.consumer;

import java.util.List;

final class StopReasonThrowable extends RuntimeException {
    private static final long serialVersionUID = 7644061262505305049L;

    private final Integer position;
    private final String content;
    private final List<? extends TrackPoints<?>> pointBranches;

    StopReasonThrowable(List<? extends TrackPoints<?>> pointBranches, String message, Integer position, String content) {
        super(message);
        this.pointBranches = pointBranches;
        this.position = position;
        this.content = content;
    }

    @SuppressWarnings("java:S1452")
    StopReason<?> reason() {
        return new StopReason<>(pointBranches, getMessage(), position, content);
    }
}
