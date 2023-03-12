package com.joutvhu.expansy.io;

import com.joutvhu.expansy.element.Branch;

import java.util.List;

/**
 * Used to select a branch when there are multiple branches.
 *
 * @author Giao Ho
 * @since 1.0.0
 */
public interface BranchSelector {
    <E> Branch<E> select(List<Branch<E>> branches);

    <E> List<Branch<E>> order(List<Branch<E>> branches);
}
