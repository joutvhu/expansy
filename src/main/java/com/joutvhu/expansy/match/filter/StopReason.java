package com.joutvhu.expansy.match.filter;

import java.util.Deque;

final class StopReason extends RuntimeException {
    private int status;
    private String message;
    private Deque<Integer> trackPoints;

    StopReason(int status, Deque<Integer> trackPoints) {
        this.status = status;
        this.trackPoints = trackPoints;
    }

    StopReason(int status, Deque<Integer> trackPoints, String message) {
        this.status = status;
        this.message = message;
        this.trackPoints = trackPoints;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Deque<Integer> getTrackPoints() {
        return trackPoints;
    }
}
