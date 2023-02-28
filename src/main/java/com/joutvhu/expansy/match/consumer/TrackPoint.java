package com.joutvhu.expansy.match.consumer;

import com.joutvhu.expansy.element.Node;

public class TrackPoint<E> {
    private int index;
    private String value;
    private Node<E> node;

    public TrackPoint(int index, String value) {
        this.index = index;
        this.value = value;
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

    public TrackPoint with(Node<E> node) {
        this.node = node;
        return this;
    }
}
