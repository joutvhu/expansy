package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.Expansy;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.parser.ExpansyParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class NumberTest {
    private ExpansyParser<BigDecimal> parser() {
        return Expansy.<BigDecimal>instance()
                .register(new Element<>() {
                    @Override
                    public void define(Definer<BigDecimal> definer) {
                        definer
                                .name("value")
                                .numeric();
                    }

                    @Override
                    public BigDecimal render(Node<BigDecimal> node) {
                        return new BigDecimal(node.getString("value"));
                    }
                })
                .selectAll();
    }

    @Test
    public void test_number_case_1() {
        BigDecimal result = parser().parseSingle("1250");
        Assertions.assertEquals(BigDecimal.valueOf(1250), result);
    }

    @Test
    public void test_number_case_2() {
        BigDecimal result = parser().parseSingle("-241");
        Assertions.assertEquals(BigDecimal.valueOf(-241), result);
    }

    @Test
    public void test_number_case_3() {
        BigDecimal result = parser().parseSingle("-241.25");
        Assertions.assertEquals(BigDecimal.valueOf(-241.25), result);
    }

    @Test
    public void test_number_case_4() {
        BigDecimal result = parser().parseSingle("0141.04");
        Assertions.assertEquals(BigDecimal.valueOf(141.04), result);
    }
}
