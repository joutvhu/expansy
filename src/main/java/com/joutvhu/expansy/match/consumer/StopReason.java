package com.joutvhu.expansy.match.consumer;

import java.util.Deque;

public final class StopReason extends RuntimeException {
    private int status;
    private Integer position;
    private String content;
    private Deque<TrackPoint> trackPoints;

    StopReason(Deque<TrackPoint> trackPoints) {
        this.status = 0;
        this.trackPoints = trackPoints;
    }

    StopReason(Deque<TrackPoint> trackPoints, String message, Integer position, String content) {
        super(message);
        this.status = 1;
        this.trackPoints = trackPoints;
        this.position = position;
        this.content = content;
    }

    public boolean isSuccess() {
        return status == 0 && trackPoints != null && !trackPoints.isEmpty();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Deque<TrackPoint> getTrackPoints() {
        return trackPoints;
    }
}
