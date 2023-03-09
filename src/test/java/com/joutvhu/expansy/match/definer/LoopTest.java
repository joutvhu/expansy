package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.Expansy;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.match.Definer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoopTest {
    @Test
    public void test_loop() {
        String result = Expansy.<String>instance()
                .register(new Element<String>() {
                    @Override
                    public void define(Definer<String> definer) {
                        definer
                                .loop()
                                .name("index")
                                .digits()
                                .name("code")
                                .alphabet()
                                .end();
                    }

                    @Override
                    public String render(Node<String> node) {
                        return "index:" + String.join(",", node.getAllString("index")) +
                                ";code:" + String.join(",", node.getAllString("code"));
                    }
                })
                .selectAll()
                .parseSingle("55A15c15r005r");
        Assertions.assertEquals("index:55,15,15,005;code:A,c,r,r", result);
    }
}
