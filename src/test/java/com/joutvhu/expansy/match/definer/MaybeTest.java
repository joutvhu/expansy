package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.Expansy;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.match.Definer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaybeTest {
    @Test
    public void test_maybe() {
        String result = Expansy.<String>instance()
                .register(new Element<String>() {
                    @Override
                    public void define(Definer<String> definer) {
                        definer
                                .maybe()
                                .name("value")
                                .word()
                                .end();
                    }

                    @Override
                    public String render(Node<String> node) {
                        return "value:" + node.getString("value");
                    }
                })
                .useAll()
                .parseSingle("vdvshr4358e_gaer");
        Assertions.assertEquals("value:vdvshr4358e_gaer", result);
    }

    @Test
    public void test_maybe_no_match() {
        String result = Expansy.<String>instance()
                .register(new Element<String>() {
                    @Override
                    public void define(Definer<String> definer) {
                        definer
                                .name("key")
                                .alphabet()
                                .name("group")
                                .maybe()
                                .name("value")
                                .numeric()
                                .end();
                    }

                    @Override
                    public String render(Node<String> node) {
                        return "key:" + node.getString("key") + ";group:" + node.getString("group") + ";value:" + node.getString("value");
                    }
                })
                .useAll()
                .parseSingle("a");
        Assertions.assertEquals("key:a;group:;value:null", result);
    }
}
