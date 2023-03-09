package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.Expansy;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.match.Definer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnregisteredTest {
    @Test
    public void test_element_1() {
        String result = Expansy.<String>instance()
                .register(new Element<>() {
                    @Override
                    public void define(Definer<String> definer) {
                        definer
                                .name("value")
                                .element(new Element<>() {
                                    @Override
                                    public void define(Definer<String> definer) {
                                        definer
                                                .equals("abc")
                                                .equals("123");
                                    }

                                    @Override
                                    public String render(Node<String> node) {
                                        return "xxx";
                                    }
                                });
                    }

                    @Override
                    public String render(Node<String> node) {
                        return "value:" + node.getNode("value").render();
                    }
                })
                .selectAll()
                .parseSingle("abc123");
        Assertions.assertEquals("value:xxx", result);
    }
}
