package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.TrackPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Deque;

@Getter
@AllArgsConstructor
final class CheckNode<E> {
    private Matcher<E> matcher;
    private TrackPoint<E> point;
    private Deque<TrackPoint<E>> trackPoints;

    CheckNode(Matcher<E> matcher, Deque<TrackPoint<E>> trackPoints) {
        this.matcher = matcher;
        this.trackPoints = trackPoints;
        this.point = trackPoints.isEmpty() ? null : trackPoints.pop();
    }

    TrackPoint<E> pop() {
        point = trackPoints.isEmpty() ? null : trackPoints.pop();
        return point;
    }
}
