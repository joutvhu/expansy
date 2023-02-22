package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.element.Result;
import com.joutvhu.expansy.exception.MathException;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.definer.DefinerUtil;
import com.joutvhu.expansy.match.filter.Consumer;
import com.joutvhu.expansy.match.filter.StopReason;
import com.joutvhu.expansy.match.filter.TrackPoint;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class ExpansyParser<E> {
    private ExpansyState<E> state;

    public ExpansyParser(ExpansyState<E> state) {
        this.state = state;
    }

    public List<Result<E>> parse(List<Element<E>> elements) {
        List<Result<E>> results = new ArrayList<>();
        for (Element<E> element : elements) {
            Params params = parse(element, 0);
            results.add(new Result<>(element, params));
        }
        return results;
    }

    public List<Result<E>> parse(List<Element<E>> elements, Consumer<E> filter) {
        List<Result<E>> results = new ArrayList<>();
        for (Element<E> element : elements) {
            Params params = parse(element, filter.offset());
            results.add(new Result<>(element, params));
        }
        results.sort(Comparator.comparingInt(Result::getLength));
        return results;
    }

    public Params parse(Element<E> element, Integer offset) {
        List<Matcher<E>> matchers = DefinerUtil.matchersOf(element);
        return parse(matchers, offset);
    }

    public Params parse(List<Matcher<E>> matchers, Integer offset) {
        Params params = new Params();
        params.setStart(offset != null ? offset : 0);
        Consumer<E> filter = new Consumer<>(state, params.getStart());
        Stack<CheckNode<E>> nodes = new Stack<>();
        for (int i = 0, len = matchers.size(); i < len; i++) {
            Matcher<E> matcher = matchers.get(i);
            try {
                matcher.match(filter);
                filter.close();
            } catch (StopReason reason) {
                Deque<TrackPoint> trackPoints = reason.getTrackPoints();
                TrackPoint trackPoint = trackPoints.pop();
                while (trackPoint == null && !nodes.empty()) {
                    // Back to other track point.
                    CheckNode<E> node = nodes.pop();
                    trackPoints = node.trackPoints;
                    trackPoint = trackPoints.pop();
                    i = nodes.size();
                }

                if (trackPoint != null) {
                    CheckNode<E> node = new CheckNode<>(matcher, trackPoint, trackPoints);
                    nodes.push(node);
                    filter = new Consumer<>(state, trackPoint.getIndex() + 1);
                } else if (i == 0) {
                    if (StringUtils.isBlank(reason.getMessage())) {
                        if (trackPoints.isEmpty())
                            throw new MathException("No track point found.");
                    }
                    throw new MathException(reason.getMessage());
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (CheckNode<E> node : nodes) {
            Matcher<E> matcher = node.matcher;
            if (matcher.getName() != null)
                params.add(matcher.getName(), node.point.getValue());
            // Join all value of nodes
            builder.append(node.point.getValue());
        }
        params.setEnd(nodes.lastElement().point.getIndex());
        params.setValue(builder.toString());

        return params;
    }

    @AllArgsConstructor
    private class CheckNode<E> {
        private Matcher<E> matcher;
        private TrackPoint point;
        private Deque<TrackPoint> trackPoints;
    }
}
