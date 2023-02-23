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
    public void test() {
        List<String> result = expansy.selectAll().parse("122+774");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("AddSubtract(Number(122)+Number(774))", result.get(0));
    }
}
