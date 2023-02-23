package com.joutvhu.expansy.io;

import com.joutvhu.expansy.element.Branch;

import java.util.Comparator;
import java.util.List;

public class DefaultSelector implements BranchSelector {
    @Override
    public <E> Branch<E> select(List<Branch<E>> branches) {
        return order(branches).stream().findFirst().orElse(null);
    }

    @Override
    public <E> List<Branch<E>> order(List<Branch<E>> branches) {
        branches.sort(Comparator.comparingInt(Branch::size));
        return branches;
    }
}
