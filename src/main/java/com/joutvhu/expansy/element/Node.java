package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.consumer.TrackPoints;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Node<E> {
    private Node<E> parent;
    private Map<String, List<Object>> children = new HashMap<>();
    private String value;
    private int start;
    private int end;
    private Element<E> element;
    private TrackPoints<E> trackPoints;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public E render() {
        if (element != null)
            return element.render(this);
        return null;
    }

    public boolean isEmpty() {
        return value.length() == 0 && children.isEmpty();
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

    public Node<E> getParent() {
        return parent;
    }

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    public TrackPoints<E> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(TrackPoints<E> trackPoints) {
        this.trackPoints = trackPoints;
    }

    public void add(String key, String value) {
        List<Object> values = children.get(key);
        if (values == null) {
            values = new ArrayList<>();
            children.put(key, values);
        }
        values.add(value);
    }

    public void add(String key, Node<E> value) {
        List<Object> values = children.get(key);
        if (values == null) {
            values = new ArrayList<>();
            children.put(key, values);
        }
        value.setParent(this);
        values.add(value);
    }

    public void addAll(String key, List<Object> objects) {
        List<Object> values = children.get(key);
        if (values == null) {
            values = new ArrayList<>();
            children.put(key, values);
        }
        for (Object o : objects) {
            if (o instanceof String)
                values.add(o);
            else if (o instanceof Node) {
                ((Node<E>) o).setParent(this);
                values.add(o);
            }
        }
    }

    public void addBase(Node<E> value) {
        for (Map.Entry<String, List<Object>> entry : value.children.entrySet()) {
            List<Object> objects = children.get(entry.getKey());
            if (objects == null)
                children.put(entry.getKey(), entry.getValue());
            else
                objects.addAll(0, entry.getValue());
        }
    }

    public void addAll(Node<E> value) {
        value.children.forEach(this::addAll);
    }

    public <R> R get(String key, int index) {
        List<Object> values = children.get(key);
        if (values != null)
            return (R) values.get(index);
        return null;
    }

    public String getAsString(String key) {
        List<Object> values = children.get(key);
        if (values == null)
            return null;
        for (Object value : values) {
            if (value instanceof String)
                return (String) value;
            if (value instanceof Node)
                return ((Node<E>) value).getValue();
        }
        return null;
    }

    public String getAsString(String key, int index) {
        Object value = get(key, index);
        if (value instanceof String)
            return (String) value;
        if (value instanceof Node)
            return ((Node<E>) value).getValue();
        return null;
    }

    public Collection<String> getAllAsString(String key) {
        List<Object> values = children.get(key);
        if (values == null)
            return new ArrayList<>();
        return values.stream()
                .map(value -> {
                    if (value instanceof String)
                        return (String) value;
                    if (value instanceof Node)
                        return ((Node<E>) value).getValue();
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public String getString(String key) {
        List<Object> values = children.get(key);
        if (values == null)
            return null;
        for (Object value : values) {
            if (value instanceof String)
                return (String) value;
        }
        return null;
    }

    public String getString(String key, int index) {
        Object value = get(key, index);
        if (value instanceof String)
            return (String) value;
        return null;
    }

    public Collection<String> getAllString(String key) {
        List<Object> values = children.get(key);
        if (values == null)
            return new ArrayList<>();
        return values.stream()
                .map(value -> {
                    if (value instanceof String)
                        return (String) value;
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public String getStringAt(String key, int index) {
        List<Object> values = children.get(key);
        if (values == null)
            return null;
        return values.stream()
                .map(value -> {
                    if (value instanceof String)
                        return (String) value;
                    return null;
                })
                .filter(Objects::nonNull)
                .skip(index)
                .findFirst()
                .orElse(null);
    }

    public Node<E> getNode(String key) {
        List<Object> values = children.get(key);
        if (values == null)
            return null;
        for (Object value : values) {
            if (value instanceof Node)
                return (Node<E>) value;
        }
        return null;
    }

    public Node<E> getNode(String key, int index) {
        Object value = get(key, index);
        if (value instanceof Node)
            return (Node<E>) value;
        return null;
    }

    public Collection<Node<E>> getAllNodes(String key) {
        List<Object> values = children.get(key);
        if (values == null)
            return new ArrayList<>();
        return values.stream()
                .filter(value -> value instanceof Node)
                .map(value -> (Node<E>) value)
                .collect(Collectors.toList());
    }

    public Node<E> getNodeAt(String key, int index) {
        List<Object> values = children.get(key);
        if (values == null)
            return null;
        return values.stream()
                .filter(value -> value instanceof Node)
                .map(value -> (Node<E>) value)
                .skip(index)
                .findFirst()
                .orElse(null);
    }

    public Node<E> clone() {
        Node<E> result = new Node<>();
        result.setParent(parent);
        result.setStart(start);
        result.setEnd(end);
        result.setValue(value);
        result.setElement(element);
        result.setTrackPoints(trackPoints);
        children.forEach((key, values) -> result.children.put(key, new ArrayList<>(values)));
        return result;
    }
}
