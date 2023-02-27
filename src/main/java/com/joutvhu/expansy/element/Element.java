package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public abstract class Element<E> {
    /**
     * Get name of the element, default is class name.
     */
    public String name() {
        return this.getClass().getSimpleName();
    }

    /**
     * Define the structure of the element
     */
    public abstract void define(Definer<E> definer);

    /**
     * Render the element, create executable component.
     */
    public abstract E render(Node<E> node);
}
