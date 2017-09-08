package com.db.mathservice.business;

import com.db.mathservice.data.*;
import lombok.AllArgsConstructor;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service("math.simple_arithmetic")
public class SimpleArithmeticExerciseGenerator implements ExerciseGenerator {
    private DigitsFiller digitsFiller;
    private ArgumentSubstituterWithValidation argumentSubstituter;

    @Autowired
    public SimpleArithmeticExerciseGenerator(DigitsFiller digitsFiller, ArgumentSubstituterWithValidation argumentSubstituter) {
        this.digitsFiller = digitsFiller;
        this.argumentSubstituter = argumentSubstituter;
    }

    @Override
    public Exercise generateExercise(ExerciseConfiguration exerciseConfiguration) {
        String template = exerciseConfiguration.getTemplate();
        Map<String, Range> rangeMap = exerciseConfiguration.getVariables().stream()
                .collect(Collectors.toMap(VariableConstraint::getVarName, o -> new Range(o.getFrom(), o.getTo())));
        template = digitsFiller.fillStringWithMissingDigits(template);

        Expression expression = new Expression(template);

        if (!expression.checkLexSyntax()) {
            throw new IllegalStateException(expression.getErrorMessage());
        }

        expression = argumentSubstituter.substituteArguments(expression, rangeMap);

        return new ArithmeticExercise(expression.getExpressionString(),
                Integer.valueOf(Double.valueOf(expression.calculate()).intValue()).toString());
    }
}
