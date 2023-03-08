package com.joutvhu.expansy.match.consumer;

import com.joutvhu.expansy.match.Matcher;

import java.util.List;

public final class StopReason<E> {
    private final String message;
    private final Integer position;
    private final String content;
    private final List<TrackPoints<E>> pointBranches;

    StopReason(List<?> pointBranches, String message, Integer position, String content) {
        this.message = message;
        this.pointBranches = (List<TrackPoints<E>>) pointBranches;
        this.position = position;
        this.content = content;
    }

    public boolean isSuccess() {
        return pointBranches != null && !pointBranches.isEmpty();
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

    public boolean isSingle() {
        return pointBranches != null && pointBranches.size() == 1;
    }

    public TrackPoints<E> getTrackPoints() {
        return pointBranches == null || pointBranches.isEmpty() ? null : pointBranches.get(0);
    }

    public List<TrackPoints<E>> getPointBranches() {
        return pointBranches;
    }

    public void setMatcher(Matcher<E> matcher) {
        for (TrackPoints<E> trackPoints : pointBranches) {
            trackPoints.setMatcher(matcher);
        }
    }

    public static <E> StopReason<E> of(Matcher<E> matcher, Consumer<E> consumer) {
        StopReason<E> reason = null;
        try {
            matcher.match(consumer);
            reason = new StopReason<>(consumer.pointBranches(), null,
                    consumer.current().getIndex(), consumer.current().getValue());
        } catch (StopReasonThrowable e) {
            reason = (StopReason<E>) e.reason();
        }
        reason.setMatcher(matcher);
        return reason;
    }

    public static <E> StopReason<E> or(StopReason<E> e1, StopReason<E> e2) {
        if (e2 == null)
            return e1;
        if (e1 == null)
            return e2;
        if (e2.getPosition() == null)
            return e1;
        if (e1.getPosition() == null)
            return e2;
        if (e1.getPosition() < e2.getPosition())
            return e2;
        return e1;
    }
}
