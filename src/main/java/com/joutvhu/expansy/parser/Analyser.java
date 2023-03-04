package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;

import java.util.Collection;
import java.util.List;

public interface Analyser<E> {
    List<Branch<E>> analyse(Collection<Element<E>> elements);

    List<Branch<E>> analyse(Collection<Element<E>> elements, Consumer<E> consumer);

    List<Branch<E>> analyse(Collection<Element<E>> elements, Integer offset, Branch<E> branch);

    List<Node<E>> analyseElements(Collection<Element<E>> elements, Consumer<E> consumer);

    List<Node<E>> analyseElements(Collection<Element<E>> elements, Integer offset, Branch<E> branch);

    Node<E> analyseElement(Element<E> element, Consumer<E> consumer);

    Node<E> analyseElement(Element<E> element, Integer offset, Branch<E> branch);

    Node<E> analyseMatchers(Collection<Matcher<E>> matchers, Consumer<E> consumer);

    Node<E> analyseMatchers(Collection<Matcher<E>> matchers, Integer offset, Branch<E> branch);
}
