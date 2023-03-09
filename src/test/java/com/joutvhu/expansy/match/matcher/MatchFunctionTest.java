package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.Expansy;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.exception.MatchException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.consumer.StopPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatchFunctionTest {
    @Test
    public void test_func_1() {
        String result = Expansy.<String>instance()
                .register(new Element<>() {
                    @Override
                    public void define(Definer<String> definer) {
                        definer
                                .name("value")
                                .check(consumer -> {
                                    StopPoint point = consumer.next(3);
                                    if (point != null && "abc".equals(point.getValue()))
                                        consumer.complete();
                                    consumer.error("Not match.");
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
    public void test_func_2() {
        Assertions.assertThrows(MatchException.class, () -> {
            Expansy.<String>instance()
                    .register(new Element<>() {
                        @Override
                        public void define(Definer<String> definer) {
                            definer
                                    .name("value")
                                    .check(consumer -> {
                                        StopPoint point = consumer.next(3);
                                        if (point != null && "abc".equals(point.getValue()))
                                            consumer.complete();
                                        consumer.error("Not match.");
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
