package com.joutvhu.expansy.match.consumer;

import com.joutvhu.expansy.element.Node;

public class TrackPoint {
    private int index;
    private String value;
    private Node<?> node;

    public TrackPoint(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public TrackPoint(Node<?> node) {
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

    public <E> Node<E> getNode() {
        return (Node<E>) node;
    }

    public TrackPoint with(Node<?> node) {
        this.node = node;
        return this;
    }
}
