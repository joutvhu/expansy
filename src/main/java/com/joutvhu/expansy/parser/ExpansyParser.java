package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.element.Result;
import com.joutvhu.expansy.exception.MathException;
import com.joutvhu.expansy.io.ProxySource;
import com.joutvhu.expansy.io.Source;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.definer.DefinerUtil;
import com.joutvhu.expansy.match.filter.LinearConsumer;
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

    public List<Result<E>> checkElements(List<Element<E>> elements) {
        List<Result<E>> results = new ArrayList<>();
        for (Element<E> element : elements) {
            Params params = checkElement(element, state);
            results.add(new Result<>(element, params));
        }
        return results;
    }

    public List<Result<E>> checkElements(List<Element<E>> elements, LinearConsumer<E> filter) {
        List<Result<E>> results = new ArrayList<>();
        Source source = new ProxySource(filter);
        for (Element<E> element : elements) {
            source.back(0);
            ExpansyState<E> childState = state.copyWith(source);
            Params params = checkElement(element, childState);
            results.add(new Result<>(element, params));
        }
        results.sort(Comparator.comparingInt(Result::getLength));
        return results;
    }

    public Params checkElement(Element<E> element, ExpansyState<E> state) {
        List<Matcher<E>> matchers = DefinerUtil.matchersOf(element);
        return checkMatchers(matchers, state);
    }

    public Params checkMatchers(List<Matcher<E>> matchers, ExpansyState<E> state) {
        Params params = new Params();
        LinearConsumer<E> filter = new LinearConsumer<>(state);
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
                    state.getSource().back(trackPoint.getIndex() + 1);
                    filter = new LinearConsumer<E>(state, trackPoint.getIndex() + 1);
                    CheckNode<E> node = new CheckNode<>(matcher, trackPoint, trackPoints);
                    nodes.push(node);
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
