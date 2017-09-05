package com.db.mathservice.business;

import com.db.mathservice.data.Exercise;
import com.db.mathservice.data.ExerciseConfiguration;
import com.db.mathservice.data.Range;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExerciseGeneratorImpl implements ExerciseGenerator {
    private DigitsFiller digitsFiller;
    private ArgumentSubstitutor argumentSubstitutor;

    public ExerciseGeneratorImpl(DigitsFiller digitsFiller) {
        this.digitsFiller = digitsFiller;
    }

    @Autowired
    public ExerciseGeneratorImpl(DigitsFiller digitsFiller, ArgumentSubstitutor argumentSubstitutor) {
        this.digitsFiller = digitsFiller;
        this.argumentSubstitutor = argumentSubstitutor;
    }

    public ExerciseGeneratorImpl(ArgumentSubstitutor argumentSubstitutor) {
        this.argumentSubstitutor = argumentSubstitutor;
    }

    @Override
    public Exercise generateExercise(ExerciseConfiguration exerciseConfiguration) {
        String format = exerciseConfiguration.getFormat();
        Map<String, Range> rangeMap = exerciseConfiguration.getRanges();
        format = digitsFiller.fillStringWithMissingDigits(format);

        Expression expression = new Expression(format);
        if (!expression.checkLexSyntax()) {
            throw new IllegalStateException(expression.getErrorMessage());
        }

        expression = argumentSubstitutor.substituteArguments(expression, rangeMap);

        return new Exercise(expression.getExpressionString(),
                Integer.valueOf(Double.valueOf(expression.calculate()).intValue()).toString());
    }
}
