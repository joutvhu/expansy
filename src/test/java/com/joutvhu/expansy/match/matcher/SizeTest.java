package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.Expansy;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.match.Definer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SizeTest {
    @Test
    public void test_size_1() {
        String result = Expansy.<String>instance()
                .register(new Element<String>() {
                    @Override
                    public void define(Definer<String> definer) {
                        definer
                                .name("value")
                                .size(6);
                    }

                    @Override
                    public String render(Node<String> node) {
                        return "value:" + node.getString("value");
                    }
                })
                .useAll()
                .parseSingle("qwerty");
        Assertions.assertEquals("value:qwerty", result);
    }

    @Test
    public void test_size_2() {
        Assertions.assertThrows(Exception.class, () -> {
            Expansy.<String>instance()
                    .register(new Element<String>() {
                        @Override
                        public void define(Definer<String> definer) {
                            definer
                                    .name("value")
                                    .size(6);
                        }

                        @Override
                        public String render(Node<String> node) {
                            return "value:" + node.getString("value");
                        }
                    })
                    .useAll()
                    .parseSingle("qwert");
        });
    }
}
