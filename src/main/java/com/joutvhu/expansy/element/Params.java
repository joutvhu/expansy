package com.joutvhu.expansy.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Params extends HashMap<String, List<String>> {
    private String value;
    private int start;
    private int end;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void add(String key, String value) {
        List<String> values = get(key);
        if (values == null) {
            values = new ArrayList<>();
            put(key, values);
        }
        values.add(value);
    }

    public String getOne(String key) {
        return getOne(key, 0);
    }

    public String getOne(String key, int index) {
        List<String> values = get(key);
        if (values != null)
            return values.get(index);
        return null;
    }
}
