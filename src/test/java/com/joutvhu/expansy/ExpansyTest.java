package com.joutvhu.expansy;

import com.joutvhu.expansy.element.AddSubtract;
import com.joutvhu.expansy.element.Function;
import com.joutvhu.expansy.element.Group;
import com.joutvhu.expansy.element.MultiplyDivision;
import com.joutvhu.expansy.element.Number;
import com.joutvhu.expansy.element.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExpansyTest {
    private Expansy<String> expansy;

    @BeforeAll
    public void setup() {
        expansy = Expansy.<String>instance()
                .register(new AddSubtract())
                .register(new Function())
                .register(new Group())
                .register(new MultiplyDivision())
                .register(new Number())
                .register(new Variable());
    }

    @Test
    public void test_add() {
        List<String> result = expansy.selectAll().parse("122+774");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("AS(N(122)+N(774))", result.get(0));
    }

    @Test
    public void test_subtract() {
        List<String> result = expansy.selectAll().parse("-225.122-77.34");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("AS(N(-225.122)-N(77.34))", result.get(0));
    }

    @Test
    public void test_multiply() {
        List<String> result = expansy.selectAll().parse("25.122 * 74.01");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("MD(N(25.122)*N(74.01))", result.get(0));
    }

    @Test
    public void test_division() {
        List<String> result = expansy.selectAll().parse("25.122 / 0.01");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("MD(N(25.122)/N(0.01))", result.get(0));
    }

    @Test
    public void test_variable() {
        List<String> result = expansy.selectAll().parse("25.122 + $saf");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("AS(N(25.122)+V(saf))", result.get(0));
    }

    @Test
    public void test_group() {
        List<String> result = expansy.selectAll().parse("$d2 * (25.122 + $saf)");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("MD(V(d2)*G(AS(N(25.122)+V(saf))))", result.get(0));
    }

    @Test
    public void test_function() {
        List<String> result = expansy.selectAll().parse("#fsd(25.122 , $saf)");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("F(fsd(N(25.122), V(saf)))", result.get(0));
    }

    @Test
    public void test_prioritize_1() {
        String result = expansy.selectAll().parseSingle("12+774*54");
        Assertions.assertEquals("AS(N(12)+MD(N(774)*N(54)))", result);
    }

    @Test
    public void test_prioritize_2() {
        String result = expansy.selectAll().parseSingle("12*774+54");
        Assertions.assertEquals("AS(MD(N(12)*N(774))+N(54))", result);
    }

    @Test
    public void test_prioritize_3() {
        String result = expansy.selectAll().parseSingle("8-3+3");
        Assertions.assertEquals("AS(AS(N(8)-N(3))+N(3))", result);
    }

    @Test
    public void test_prioritize_4() {
        String result = expansy.selectAll().parseSingle("(9-3)+(8-3+3)");
        Assertions.assertEquals("AS(G(AS(N(9)-N(3)))+G(AS(AS(N(8)-N(3))+N(3))))", result);
    }
}
