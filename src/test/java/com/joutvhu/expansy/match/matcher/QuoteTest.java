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
                .register(new Element<>() {
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
                .selectAll()
                .parseSingle("'abc123'");
        Assertions.assertEquals("value:'abc123'", result);
    }

    @Test
    public void test_quote_2() {
        String result = Expansy.<String>instance()
                .register(new Element<>() {
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
                .selectAll()
                .parseSingle("'abc123'");
        Assertions.assertEquals("value:abc123", result);
    }

    @Test
    public void test_quote_3() {
        String result = Expansy.<String>instance()
                .register(new Element<>() {
                    @Override
                    public void define(Definer<String> definer) {
                        definer
                                .quote();
                    }

                    @Override
                    public String render(Node<String> node) {
                        return "value:" + node.getNode("value").getString("quote");
                    }
                })
                .selectAll()
                .parseSingle("'abc123'");
        Assertions.assertEquals("value:abc123", result);
    }
}
