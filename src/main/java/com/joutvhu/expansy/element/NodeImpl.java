package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.consumer.TrackPoints;
import com.joutvhu.expansy.parser.ExpansyState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class NodeImpl<E> implements Node<E>, Serializable {
    private static final long serialVersionUID = -5509553998914844927L;

    private final ExpansyState<E> state;
    private final Map<String, List<Object>> children = new HashMap<>();
    private NodeImpl<E> parent;
    private String value;
    private int start;
    private int end;
    private boolean embed = true;
    private Element<E> element;
    private TrackPoints<E> trackPoints;

    public NodeImpl(ExpansyState<E> state) {
        this.state = state;
    }

    @Override
    public ExpansyState<E> getState() {
        return state;
    }

    @Override
    public E render() {
        if (element != null)
            return element.render(this);
        return null;
    }

    @Override
    public boolean isEmpty() {
        return value.length() == 0 && children.isEmpty();
    }

    /**
     * Get start position
     */
    @Override
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    /**
     * Get end position
     */
    @Override
    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * Get string value of node
     */
    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Get length of string value
     */
    @Override
    public int getLength() {
        return value.length();
    }

    public boolean isEmbed() {
        return embed;
    }

    public void setEmbed(boolean embed) {
        this.embed = embed;
    }

    @Override
    public Element<E> getElement() {
        return element;
    }

    public void setElement(Element<E> element) {
        this.element = element;
    }

    /**
     * Get parent node
     */
    @Override
    public NodeImpl<E> getParent() {
        return parent;
    }

    public void setParent(NodeImpl<E> parent) {
        this.parent = parent;
    }

    public TrackPoints<E> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(TrackPoints<E> trackPoints) {
        this.trackPoints = trackPoints;
    }

    public void add(String key, String value) {
        List<Object> values = children.computeIfAbsent(key, k -> new ArrayList<>());
        values.add(value);
    }

    public void add(String key, NodeImpl<E> value) {
        List<Object> values = children.computeIfAbsent(key, k -> new ArrayList<>());
        value.setParent(this);
        values.add(value);
    }

    public void addAll(String key, List<Object> objects) {
        List<Object> values = children.computeIfAbsent(key, k -> new ArrayList<>());
        for (Object o : objects) {
            if (o instanceof String)
                values.add(o);
            else if (o instanceof NodeImpl) {
                ((NodeImpl<E>) o).setParent(this);
                values.add(o);
            }
        }
    }

    public void addBase(NodeImpl<E> value) {
        for (Map.Entry<String, List<Object>> entry : value.children.entrySet()) {
            List<Object> objects = children.get(entry.getKey());
            if (objects == null)
                children.put(entry.getKey(), entry.getValue());
            else
                objects.addAll(0, entry.getValue());
        }
    }

    public void addAll(NodeImpl<E> value) {
        value.children.forEach(this::addAll);
    }

    @Override
    public <R> R get(String key, int index) {
        List<Object> values = children.get(key);
        if (values != null)
            return (R) values.get(index);
        return null;
    }

    @Override
    public String getAsString(String key) {
        List<Object> values = children.get(key);
        if (values == null)
            return null;
        for (Object v : values) {
            if (v instanceof String)
                return (String) v;
            if (v instanceof NodeImpl)
                return ((NodeImpl<E>) v).getValue();
        }
        return null;
    }

    @Override
    public String getAsString(String key, int index) {
        Object v = get(key, index);
        if (v instanceof String)
            return (String) v;
        if (v instanceof NodeImpl)
            return ((NodeImpl<E>) v).getValue();
        return null;
    }

    @Override
    public List<String> getAllAsString(String key) {
        List<Object> values = children.get(key);
        if (values == null)
            return new ArrayList<>();
        return values.stream()
                .map(v -> {
                    if (v instanceof String)
                        return (String) v;
                    if (v instanceof NodeImpl)
                        return ((NodeImpl<E>) v).getValue();
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Get first String by key.
     */
    @Override
    public String getString(String key) {
        List<Object> values = children.get(key);
        if (values == null)
            return null;
        for (Object v : values) {
            if (v instanceof String)
                return (String) v;
        }
        return null;
    }

    @Override
    public String getString(String key, int index) {
        Object v = get(key, index);
        if (v instanceof String)
            return (String) v;
        return null;
    }

    /**
     * Get all Strings with the same key provided.
     */
    @Override
    public List<String> getAllString(String key) {
        List<Object> values = children.get(key);
        if (values == null)
            return new ArrayList<>();
        return values.stream()
                .map(v -> {
                    if (v instanceof String)
                        return (String) v;
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Get String with key and index.
     */
    @Override
    public String getStringAt(String key, int index) {
        List<Object> values = children.get(key);
        if (values == null)
            return null;
        return values.stream()
                .map(v -> {
                    if (v instanceof String)
                        return (String) v;
                    return null;
                })
                .filter(Objects::nonNull)
                .skip(index)
                .findFirst()
                .orElse(null);
    }

    /**
     * Get first Node by key.
     */
    @Override
    public Node<E> getNode(String key) {
        List<Object> values = children.get(key);
        if (values == null)
            return null;
        for (Object v : values) {
            if (v instanceof NodeImpl)
                return (NodeImpl<E>) v;
        }
        return null;
    }

    @Override
    public Node<E> getNode(String key, int index) {
        Object v = get(key, index);
        if (v instanceof NodeImpl)
            return (NodeImpl<E>) v;
        return null;
    }

    /**
     * Get all Nodes with the same key provided.
     */
    @Override
    public List<Node<E>> getAllNodes(String key) {
        List<Object> values = children.get(key);
        if (values == null)
            return new ArrayList<>();
        return values.stream()
                .filter(NodeImpl.class::isInstance)
                .map(v -> (NodeImpl<E>) v)
                .collect(Collectors.toList());
    }

    /**
     * Get Node with key and index.
     */
    @Override
    public Node<E> getNodeAt(String key, int index) {
        List<Object> values = children.get(key);
        if (values == null)
            return null;
        return values.stream()
                .filter(NodeImpl.class::isInstance)
                .map(v -> (NodeImpl<E>) v)
                .skip(index)
                .findFirst()
                .orElse(null);
    }

    @Override
    @SuppressWarnings("java:S2975")
    public NodeImpl<E> clone() {
        NodeImpl<E> result = new NodeImpl<>(state);
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
