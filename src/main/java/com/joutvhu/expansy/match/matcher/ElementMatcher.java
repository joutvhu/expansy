package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.NodeImpl;
import com.joutvhu.expansy.exception.MatchException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.parser.Analyser;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("java:S3776")
abstract class ElementMatcher<E> extends Matcher<E> {
    public ElementMatcher(Definer<E> parent) {
        super(parent);
    }

    public void match(Consumer<E> consumer, Collection<Element<E>> elements) {
        Branch<E> branch = consumer.branch();
        if (branch != null) {
            NodeImpl<E> node = branch.last();
            if (node != null && consumer.offset() == node.getEnd()) {
                Element<E> element = node.getElement();
                if (element != null) {
                    for (Element<E> type : elements) {
                        if (type.getClass().isInstance(element) && element.name().equals(type.name())) {
                            consumer.push(node);
                            consumer.close();
                        }
                    }
                }
            }
        }

        try {
            Analyser<E> analyser = consumer.state().getAnalyser();
            List<NodeImpl<E>> results = analyser.analyseElements(elements, consumer);
            for (NodeImpl<E> result : results) {
                consumer.push(result);
                consumer.fork();
            }
            consumer.close();
        } catch (MatchException e) {
            consumer.errorAt(e.getMessage(), e.getIndex(), e.getContent());
        }
    }
}
