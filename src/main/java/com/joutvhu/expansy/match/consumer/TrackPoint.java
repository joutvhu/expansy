package com.joutvhu.expansy.match.consumer;

import com.joutvhu.expansy.element.Params;

public class TrackPoint {
    private int index;
    private String value;
    private Params<?> params;

    public TrackPoint(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public TrackPoint(Params<?> params) {
        this.index = params.getEnd();
        this.value = params.getValue();
        this.params = params;
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    public <E> Params<E> getParams() {
        return (Params<E>) params;
    }

    public TrackPoint with(Params<?> params) {
        this.params = params;
        return this;
    }
}
