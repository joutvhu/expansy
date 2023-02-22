package com.joutvhu.expansy.match.consumer;

import java.util.Deque;

public final class StopReason extends RuntimeException {
    private int status;
    private String message;
    private Deque<TrackPoint> trackPoints;

    StopReason(int status, Deque<TrackPoint> trackPoints) {
        this.status = status;
        this.trackPoints = trackPoints;
    }

    StopReason(int status, Deque<TrackPoint> trackPoints, String message) {
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

    public Deque<TrackPoint> getTrackPoints() {
        return trackPoints;
    }
}
