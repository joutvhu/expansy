package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.Expansy;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.match.Definer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuoteTest {
    @Test
    public void test_quote_1() {
        String result = Expansy.<String>instance()
                .register(new Element<String>() {
                    @Override
                    public void define(Definer<String> definer) {
                        definer
                                .name("value")
                                .quote();
                    }

                    @Override
                    public String render(Node<String> node) {
                        return "value:" + node.getString("value");
                    }
                })
                .useAll()
                .parseSingle("'abc123'");
        Assertions.assertEquals("value:'abc123'", result);
    }

    @Test
    public void test_quote_2() {
        String result = Expansy.<String>instance()
                .register(new Element<String>() {
                    @Override
                    public void define(Definer<String> definer) {
                        definer
                                .name("value")
                                .quote();
                    }

                    @Override
                    public String render(Node<String> node) {
                        return "value:" + node.getNode("value").getString("quote");
                    }
                })
                .useAll()
                .parseSingle("'abc123'");
        Assertions.assertEquals("value:abc123", result);
    }

    @Test
    public void test_quote_3() {
        Assertions.assertThrows(Exception.class, () -> {
            Expansy.<String>instance()
                    .register(new Element<String>() {
                        @Override
                        public void define(Definer<String> definer) {
                            definer
                                    .name("value")
                                    .quote('\'');
                        }

                        @Override
                        public String render(Node<String> node) {
                            return "value:" + node.getNode("value").getString("quote");
                        }
                    })
                    .useAll()
                    .parseSingle("'abc123");
        });
    }

    @Test
    public void test_quote_4() {
        Assertions.assertThrows(Exception.class, () -> {
            Expansy.<String>instance()
                    .register(new Element<String>() {
                        @Override
                        public void define(Definer<String> definer) {
                            definer
                                    .name("value")
                                    .quote('\'');
                        }

                        @Override
                        public String render(Node<String> node) {
                            return "value:" + node.getNode("value").getString("quote");
                        }
                    })
                    .useAll()
                    .parseSingle("abc123'");
        });
    }
}
