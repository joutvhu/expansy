package com.joutvhu.expansy.match.consumer;

import com.joutvhu.expansy.element.Node;

import java.io.Serializable;

public class TrackPoint<E> implements Serializable {
    private static final long serialVersionUID = 4950650075805378208L;

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
