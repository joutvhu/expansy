package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.element.Result;
import com.joutvhu.expansy.exception.MathException;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.StopReason;
import com.joutvhu.expansy.match.consumer.TrackPoint;
import com.joutvhu.expansy.match.definer.DefinerUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class InternalParser<E> {
    private ExpansyState<E> state;

    public InternalParser(ExpansyState<E> state) {
        this.state = state;
        this.state.setParser(this);
    }

    public List<Result<E>> parseByElements(Collection<Element<E>> elements, int offset) {
        List<Result<E>> results = new ArrayList<>();
        for (Element<E> element : elements) {
            Params<E> params = parseByElement(element, offset);
            if (state.getLength() <= params.getEnd()) {
                Result<E> result = new Result<>();
                result.add(params);
                results.add(result);
            } else {
                List<Result<E>> values = parseByElements(elements, params.getEnd());
                results.addAll(merge(params, values));
            }
        }
        return results;
    }

    private List<Result<E>> merge(Params<E> value, List<Result<E>> values) {
        List<Result<E>> results = new ArrayList<>();
        for (Result<E> v : values) {
            Result<E> result = new Result<>();
            result.add(value);
            result.addAll(v.getValues());
            results.add(result);
        }
        return results;
    }

    public List<Params<E>> parseByElements(Collection<Element<E>> elements, Consumer<E> filter) {
        List<Params<E>> results = new ArrayList<>();
        for (Element<E> element : elements) {
            Params<E> params = parseByElement(element, filter.offset());
            results.add(params);
        }
        results.sort(Comparator.comparingInt(Params::getLength));
        return results;
    }

    public Params<E> parseByElement(Element<E> element, Integer offset) {
        List<Matcher<E>> matchers = DefinerUtil.matchersOf(element);
        Params<E> params = parseByMatchers(matchers, offset);
        params.setElement(element);
        return params;
    }

    public Params<E> parseByMatchers(Collection<Matcher<E>> matchers, Integer offset) {
        Params<E> params = new Params<>();
        params.setStart(offset != null ? offset : 0);
        Consumer<E> filter = new Consumer<>(state, params.getStart());
        Stack<CheckNode<E>> nodes = new Stack<>();
        Matcher<E>[] array = (Matcher<E>[]) matchers.toArray();
        for (int i = 0, len = matchers.size(); i < len; i++) {
            Matcher<E> matcher = array[i];
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
                    filter = new Consumer<>(state, trackPoint.getIndex());
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
