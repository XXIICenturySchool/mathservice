package com.db.mathservice.business;

import com.db.mathservice.data.ArithmeticExercise;
import com.db.mathservice.data.ExerciseConfiguration;
import com.db.mathservice.data.Range;
import com.db.mathservice.data.VariableConstraint;
import com.db.mathservice.utility.ArgumentSubstituter;
import com.db.mathservice.utility.DigitsFiller;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service("math.arithmetic")
public class ArithmeticExerciseGenerator implements ExerciseGenerator {
    private DigitsFiller digitsFiller;
    private ArgumentSubstituter argumentSubstituter;

    public ArithmeticExerciseGenerator(DigitsFiller digitsFiller) {
        this.digitsFiller = digitsFiller;
    }

    @Autowired
    public ArithmeticExerciseGenerator(DigitsFiller digitsFiller, ArgumentSubstituter argumentSubstituter) {
        this.digitsFiller = digitsFiller;
        this.argumentSubstituter = argumentSubstituter;
    }

    public ArithmeticExerciseGenerator(ArgumentSubstituter argumentSubstituter) {
        this.argumentSubstituter = argumentSubstituter;
    }

    @Override
    public ArithmeticExercise generateExercise(ExerciseConfiguration exerciseConfiguration) {
        String template = exerciseConfiguration.getTemplate();
        Map<String, Range> rangeMap = exerciseConfiguration.getVariables().stream()
                .collect(Collectors.toMap(VariableConstraint::getVarName, o -> new Range(o.getFrom(), o.getTo())));
        template = digitsFiller.fillStringWithMissingDigits(template);

        Expression expression = new Expression(template);
        if (!new Expression(template.replaceAll("<", "(").replaceAll(">", ")")).checkLexSyntax()) {
            throw new IllegalStateException(expression.getErrorMessage());
        }

        expression = argumentSubstituter.substituteArguments(expression, rangeMap);

        return new ArithmeticExercise(expression.getExpressionString(),
                Integer.valueOf(Double.valueOf(expression.calculate()).intValue()).toString());
    }
}
