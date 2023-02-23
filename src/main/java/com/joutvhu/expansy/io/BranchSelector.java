package com.joutvhu.expansy.io;

import com.joutvhu.expansy.element.Branch;

import java.util.List;

public interface BranchSelector {
    <E> Branch<E> select(List<Branch<E>> branches);

    <E> List<Branch<E>> order(List<Branch<E>> branches);
}
