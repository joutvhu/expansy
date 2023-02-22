package com.joutvhu.expansy.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Params<E>  {
    private Map<String, List<Object>> params = new HashMap<>();
    private String value;
    private int start;
    private int end;
    private Element<E> element;

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

    public int getLength() {
        return value.length();
    }

    public Element<E> getElement() {
        return element;
    }

    public void setElement(Element<E> element) {
        this.element = element;
    }

    public void add(String key, String value) {
        List<Object> values = params.get(key);
        if (values == null) {
            values = new ArrayList<>();
            params.put(key, values);
        }
        values.add(value);
    }

    public void add(String key, Params<E> value) {
        List<Object> values = params.get(key);
        if (values == null) {
            values = new ArrayList<>();
            params.put(key, values);
        }
        values.add(value);
    }

    public String getString(String key) {
        return getString(key, 0);
    }

    public String getString(String key, int index) {
        List<Object> values = params.get(key);
        if (values != null) {
            Object value = values.get(index);
            if (value instanceof String)
                return (String) value;
            if (value instanceof Params)
                return ((Params<E>) value).getValue();
            return null;
        }
        return null;
    }
}
