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
        Assertions.assertEquals("AddSubtract(Number(122)+Number(774))", result.get(0));
    }

    @Test
    public void test_subtract() {
        List<String> result = expansy.selectAll().parse("-225.122-77.34");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("AddSubtract(Number(-225.122)-Number(77.34))", result.get(0));
    }

    @Test
    public void test_multiply() {
        List<String> result = expansy.selectAll().parse("25.122 * 74.01");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("MultiplyDivision(Number(25.122)*Number(74.01))", result.get(0));
    }

    @Test
    public void test_division() {
        List<String> result = expansy.selectAll().parse("25.122 / 0.01");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("MultiplyDivision(Number(25.122)/Number(0.01))", result.get(0));
    }

    @Test
    public void test_variable() {
        List<String> result = expansy.selectAll().parse("25.122 + $saf");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("AddSubtract(Number(25.122)+Variable(saf))", result.get(0));
    }

    @Test
    public void test_group() {
        List<String> result = expansy.selectAll().parse("$d2 * (25.122 + $saf)");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("MultiplyDivision(Variable(d2)*Group(AddSubtract(Number(25.122)+Variable(saf))))", result.get(0));
    }

    @Test
    public void test_function() {
        List<String> result = expansy.selectAll().parse("#fsd(25.122 , $saf)");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Function(fsd(Number(25.122), Variable(saf)))", result.get(0));
    }
}
