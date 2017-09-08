package com.db.mathservice.utility;

import com.db.mathservice.utility.ArgumentSubstituterImpl;
import com.db.mathservice.data.Range;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArgumentSubstituterImplTest {
    @Autowired
    ArgumentSubstituterImpl argumentSubstitutorImpl;

    @Test
    public void tempTest() throws Exception {
        Map<String, Range> rangeMap = new HashMap<>();
        rangeMap.put("a", new Range(2, 2));
        rangeMap.put("b", new Range(3, 3));
        rangeMap.put("c", new Range(4, 4));
        Expression expression = argumentSubstitutorImpl
                .substituteArguments(new Expression("<c + <a*b>>/b"), rangeMap);
        assertEquals("10/3", expression.getExpressionString());
    }

    @Test
    public void expressionWithNoArgumentsDoesNotSubstitute() throws Exception {
        Expression originalExpression = new Expression("1");
        Expression expression = argumentSubstitutorImpl
                .substituteArguments(originalExpression, null);
        assertEquals("1", expression.getExpressionString());
    }

    @Test
    public void expressionWithOneArgumentsOnlySubstitutes() throws Exception {
        Map<String, Range> rangeMap = new HashMap<>();
        rangeMap.put("a", new Range(1, 1));
        Expression expression = argumentSubstitutorImpl
                .substituteArguments(new Expression("a"), rangeMap);
        assertEquals("1", expression.getExpressionString());
    }

}