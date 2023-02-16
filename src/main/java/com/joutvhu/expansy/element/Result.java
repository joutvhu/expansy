package com.joutvhu.expansy.element;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result<E> {
    private Element<E> element;
    private Params params;
}
