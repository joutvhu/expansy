package com.joutvhu.expansy.match;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.exception.MathException;
import com.joutvhu.expansy.match.definer.DefinerUtil;
import com.joutvhu.expansy.match.filter.LinearFilter;
import com.joutvhu.expansy.match.filter.StopReason;
import com.joutvhu.expansy.match.filter.TrackPoint;
import com.joutvhu.expansy.parser.ExpansyConfig;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class Checker<E> {
    private ExpansyConfig<E> config;

    public Params check(Element<E> element) throws IOException {
        List<Matcher<E>> matchers = DefinerUtil.matchersOf(element);
        return check(matchers);
    }

    public Params check(List<Matcher<E>> matchers) throws IOException {
        Params params = new Params();
        LinearFilter<E> filter = new LinearFilter<E>(config);
        Stack<CheckNode> nodes = new Stack<>();
        for (int i = 0, len = matchers.size(); i < len; i++) {
            Matcher<E> matcher = matchers.get(i);
            try {
                matcher.match(filter);
                throw new MathException("No track point found.");
            } catch (StopReason reason) {
                Deque<TrackPoint> trackPoints = reason.getTrackPoints();
                TrackPoint trackPoint = trackPoints.pop();
                while (trackPoint == null && !nodes.empty()) {
                    // Back to other track point.
                    CheckNode node = nodes.pop();
                    trackPoints = node.trackPoints;
                    trackPoint = trackPoints.pop();
                    i = nodes.size();
                }

                if (trackPoint != null) {
                    config.getSource().back(trackPoint.getIndex());
                    filter = new LinearFilter<E>(config, trackPoint.getIndex());
                    CheckNode node = new CheckNode(matcher, trackPoint, trackPoints);
                    nodes.push(node);
                } else if (i == 0) {
                    throw new MathException(reason.getMessage());
                }
            }
        }

        for (CheckNode node : nodes) {
            Matcher<E> matcher = node.matcher;
            if (matcher.name != null)
                params.add(matcher.name, node.point.getValue());
        }
        return params;
    }

    @AllArgsConstructor
    class CheckNode {
        private Matcher<E> matcher;
        private TrackPoint point;
        private Deque<TrackPoint> trackPoints;
    }
}
