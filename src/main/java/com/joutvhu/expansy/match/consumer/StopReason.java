package com.joutvhu.expansy.match.consumer;

import com.joutvhu.expansy.match.Matcher;

import java.util.Deque;

public final class StopReason<E> {
    private final String message;
    private final Integer position;
    private final String content;
    private final Deque<TrackPoint<E>> trackPoints;

    StopReason(Deque<?> trackPoints, String message, Integer position, String content) {
        this.message = message;
        this.trackPoints = (Deque<TrackPoint<E>>) trackPoints;
        this.position = position;
        this.content = content;
    }

    public boolean isSuccess() {
        return message == null && trackPoints != null && !trackPoints.isEmpty();
    }

    public String getMessage() {
        return message;
    }

    public Integer getPosition() {
        return position;
    }

    public String getContent() {
        return content;
    }

    public Deque<TrackPoint<E>> getTrackPoints() {
        return trackPoints;
    }

    public static <E> StopReason<E> of(Matcher<E> matcher, Consumer<E> consumer) {
        try {
            matcher.match(consumer);
            consumer.close();
            return null;
        } catch (StopReasonThrowable e) {
            return (StopReason<E>) e.reason();
        }
    }
}
