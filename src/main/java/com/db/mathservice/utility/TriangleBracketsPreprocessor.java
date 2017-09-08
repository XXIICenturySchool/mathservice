package com.db.mathservice.utility;

import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.stereotype.Service;

import java.util.Deque;
import java.util.LinkedList;

@Service
public class TriangleBracketsPreprocessor implements TemplatePreprocessor {

    public String process(String template) {
        Deque<StringBuilder> partialExpressions = new LinkedList<>();
        partialExpressions.push(new StringBuilder());
        for (int i = 0; i < template.length(); i++) {
            if (template.charAt(i) == '<') {
                partialExpressions.push(new StringBuilder());
            } else if (template.charAt(i) == '>') {
                String substring = partialExpressions.poll().toString();
                partialExpressions.peek().append(evaluateSubexpression(substring));
            } else {
                partialExpressions.peek().append(template.substring(i,i+1));
            }
        }
        return partialExpressions.poll().toString();
    }

    private String evaluateSubexpression(String subexpression) {
        return Integer.valueOf(
                Double.valueOf(
                        new Expression(subexpression).calculate()
                ).intValue()
        ).toString();
    }

}
