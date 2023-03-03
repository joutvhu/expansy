package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.Expansy;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.exception.MatchException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.consumer.StopPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoolFunctionTest {
    @Test
    public void test_func_case_1() {
        String result = Expansy.<String>instance()
                .register(new Element<>() {
                    @Override
                    public void define(Definer<String> definer) {
                        definer
                                .name("value")
                                .check(value -> {
                                    if (value == null || value.length() > 3)
                                        return false;
                                    if ("abc".equals(value))
                                        return true;
                                    return null;
                                });
                    }

                    @Override
                    public String render(Node<String> node) {
                        return "value:" + node.getString("value");
                    }
                })
                .selectAll()
                .parseSingle("abc");
        Assertions.assertEquals("value:abc", result);
    }

    @Test
    public void test_func_case_2() {
        Assertions.assertThrows(MatchException.class, () -> {
            Expansy.<String>instance()
                    .register(new Element<>() {
                        @Override
                        public void define(Definer<String> definer) {
                            definer
                                    .name("value")
                                    .check(value -> {
                                        if (value == null || value.length() > 3)
                                            return false;
                                        if ("abc".equals(value))
                                            return true;
                                        return null;
                                    });
                        }

                        @Override
                        public String render(Node<String> node) {
                            return "value:" + node.getString("value");
                        }
                    })
                    .selectAll()
                    .parseSingle("wqfe");
        });
    }
}
