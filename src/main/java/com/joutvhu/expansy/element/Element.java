package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public interface Element<E> {
    /**
     * Get name of the element, default is class name.
     */
    default String name() {
        return this.getClass().getSimpleName();
    }

    /**
     * Define the structure of the element
     */
    void define(Definer<E> definer);

    /**
     * Render the element, create executable component.
     */
    E render(Node<E> node);
}
