package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.Expansy;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.match.Definer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrTest {
    @Test
    public void test_or_case_1() {
        String result = Expansy.<String>instance()
                .register(new Element<String>() {
                    @Override
                    public void define(Definer<String> definer) {
                        definer
                                .or()
                                .name("value")
                                .uppercases(3)
                                .or()
                                .name("value")
                                .lowercases(3)
                                .end();
                    }

                    @Override
                    public String render(Node<String> node) {
                        return "value:" + node.getString("value");
                    }
                })
                .selectAll()
                .parseSingle("FGH");
        Assertions.assertEquals("value:FGH", result);
    }

    @Test
    public void test_or_case_2() {
        String result = Expansy.<String>instance()
                .register(new Element<String>() {
                    @Override
                    public void define(Definer<String> definer) {
                        definer
                                .or()
                                .name("value")
                                .uppercases(3)
                                .or()
                                .name("value")
                                .lowercases(3)
                                .end();
                    }

                    @Override
                    public String render(Node<String> node) {
                        return "value:" + node.getString("value");
                    }
                })
                .selectAll()
                .parseSingle("ftd");
        Assertions.assertEquals("value:ftd", result);
    }
}
