package com.joutvhu.expansy.match.consumer;

import com.joutvhu.expansy.element.NodeImpl;
import com.joutvhu.expansy.match.Matcher;

import java.util.LinkedList;

public class TrackPoints<E> extends LinkedList<TrackPoint<E>> {
    private static final long serialVersionUID = 7007896680256218776L;

    private Matcher<E> matcher;
    private TrackPoint<E> current;

    public Matcher<E> getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher<E> matcher) {
        this.matcher = matcher;
    }

    public NodeImpl<E> lastNode() {
        TrackPoint<E> point = getLast();
        return point != null ? point.getNode() : null;
    }

    public TrackPoint<E> trackPoint() {
        return current;
    }

    public TrackPoint<E> next() {
        current = isEmpty() ? null : pop();
        return current;
    }

    @Override
    public TrackPoints<E> clone() {
        TrackPoints<E> trackPoints = new TrackPoints<>();
        trackPoints.addAll(this);
        trackPoints.current = current;
        trackPoints.matcher = matcher;
        return trackPoints;
    }
}
