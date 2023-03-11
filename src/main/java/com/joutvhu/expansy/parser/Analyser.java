package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.NodeImpl;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.TrackPoint;
import com.joutvhu.expansy.match.consumer.TrackPoints;

import java.util.Collection;
import java.util.List;

public interface Analyser<E> {
    List<Branch<E>> analyse(Collection<Element<E>> elements);

    List<Branch<E>> analyse(Collection<Element<E>> elements, Consumer<E> consumer);

    List<Branch<E>> analyse(Collection<Element<E>> elements, Integer offset, Branch<E> branch);

    List<NodeImpl<E>> analyseElements(Collection<Element<E>> elements, Consumer<E> consumer);

    List<NodeImpl<E>> analyseElements(Collection<Element<E>> elements, Integer offset, Branch<E> branch);

    List<NodeImpl<E>> analyseElement(Element<E> element, Consumer<E> consumer);

    List<NodeImpl<E>> analyseElement(Element<E> element, Integer offset, Branch<E> branch);

    List<NodeImpl<E>> analyseMatchers(Collection<Matcher<E>> matchers, Consumer<E> consumer);

    List<NodeImpl<E>> analyseMatchers(Collection<Matcher<E>> matchers, Integer offset, Branch<E> branch);

    List<NodeImpl<E>> analyseMatchers(Collection<Matcher<E>> matchers, List<NodeImpl<E>> nodes, Branch<E> branch);

    List<NodeImpl<E>> analyseMatchers(Collection<Matcher<E>> matchers, NodeImpl<E> node, Branch<E> branch);

    List<NodeImpl<E>> analyseMatchers(Collection<Matcher<E>> matchers, Branch<E> branch, List<TrackPoints<E>> cases);

    List<NodeImpl<E>> analyseMatchers(Collection<Matcher<E>> matchers, Branch<E> branch, TrackPoints<E> trackPoints);

    List<NodeImpl<E>> analyseMatchers(Collection<Matcher<E>> matchers, Branch<E> branch, TrackPoint<E> trackPoint);
}
