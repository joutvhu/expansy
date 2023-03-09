package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.StopPoint;

public class QuoteMatcher<E> extends Matcher<E> {
    private final char[] types;

    public QuoteMatcher(Definer<E> parent) {
        this(parent, '"', '\'');
    }

    public QuoteMatcher(Definer<E> parent, char... types) {
        super(parent);
        this.types = types;
    }

    @Override
    public void match(Consumer<E> consumer) {
        StopPoint point = consumer.next();
        if (point == null)
            consumer.error("Not found any character.");
        char type = point.getCharacter();
        if (!contains(type))
            consumer.error("The '{0}'is not a quote symbol.", point.getCharacter());
        boolean ignore = false;
        do {
            point = consumer.next();
            if (point == null)
                consumer.error("Quote doesn't end.");
            if (ignore) {
                ignore = '\\' == point.getCharacter();
                continue;
            }
            ignore = '\\' == point.getCharacter();
        } while (type != point.getCharacter());
        String value = point.getValue();
        if (value.length() > 1) {
            String quoteValue = value.substring(1, value.length() - 1);
            Node<E> node = new Node<>();
            node.setStart(point.getOffset());
            node.setEnd(point.getIndex());
            node.setEmbed(false);
            node.setValue(value);
            node.add("quote", quoteValue);
            consumer.push(node);
            consumer.close();
        } else
            consumer.complete();
    }

    private boolean contains(char character) {
        for (char c : types) {
            if (c == character)
                return true;
        }
        return false;
    }
}
