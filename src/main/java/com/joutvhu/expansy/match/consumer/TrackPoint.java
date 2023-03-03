package com.joutvhu.expansy.match.consumer;

import com.joutvhu.expansy.element.Node;

public class TrackPoint<E> {
    private final int index;
    private final String value;
    private final Node<E> node;

    public TrackPoint(int index, String value) {
        this.index = index;
        this.value = value;
        this.node = null;
    }

    public TrackPoint(Node<E> node) {
        this.index = node.getEnd();
        this.value = node.getValue();
        this.node = node;
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    public Node<E> getNode() {
        return node;
    }
}
