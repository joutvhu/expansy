package com.joutvhu.expansy.match.consumer;

import com.joutvhu.expansy.match.Matcher;

import java.io.Serializable;
import java.util.List;

public final class StopReason<E> implements Serializable {
    private static final long serialVersionUID = 8704070960054730674L;

    private final String message;
    private final Integer position;
    private final String content;
    private final List<TrackPoints<E>> cases;

    StopReason(List<?> cases, String message, Integer position, String content) {
        this.message = message;
        this.cases = (List<TrackPoints<E>>) cases;
        this.position = position;
        this.content = content;
    }

    public boolean isSuccess() {
        return cases != null && !cases.isEmpty();
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
        return cases != null && cases.size() == 1;
    }

    public TrackPoints<E> getTrackPoints() {
        return cases == null || cases.isEmpty() ? null : cases.get(0);
    }

    public List<TrackPoints<E>> getCases() {
        return cases;
    }

    public void setMatcher(Matcher<E> matcher) {
        for (TrackPoints<E> trackPoints : cases) {
            trackPoints.setMatcher(matcher);
        }
    }

    public static <E> StopReason<E> of(Matcher<E> matcher, Consumer<E> consumer) {
        StopReason<E> reason = null;
        try {
            matcher.match(consumer);
            reason = new StopReason<>(consumer.cases(), null,
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

    public static void throwReason(Throwable e) {
        if (e instanceof StopReasonThrowable)
            throw (StopReasonThrowable) e;
    }
}
