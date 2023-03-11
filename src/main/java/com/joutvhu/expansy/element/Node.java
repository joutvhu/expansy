package com.joutvhu.expansy.element;

import com.joutvhu.expansy.parser.ExpansyState;

import java.util.List;

public interface Node<E> {
    ExpansyState<E> getState();

    E render();

    boolean isEmpty();

    int getStart();

    int getEnd();

    String getValue();

    int getLength();

    Element<E> getElement();

    Node<E> getParent();

    <R> R get(String key, int index);

    String getAsString(String key);

    String getAsString(String key, int index);

    List<String> getAllAsString(String key);

    /**
     * Get first String by key.
     */
    String getString(String key);

    String getString(String key, int index);

    /**
     * Get all Strings with the same key provided.
     */
    List<String> getAllString(String key);

    /**
     * Get String with key and index.
     */
    String getStringAt(String key, int index);

    /**
     * Get first Node by key.
     */
    Node<E> getNode(String key);

    Node<E> getNode(String key, int index);

    /**
     * Get all Nodes with the same key provided.
     */
    List<Node<E>> getAllNodes(String key);

    /**
     * Get Node with key and index.
     */
    Node<E> getNodeAt(String key, int index);
}
