package com.db.mathservice.business;

import com.db.mathservice.data.Equation;
import com.db.mathservice.data.ExerciseConfiguration;
import com.db.mathservice.data.Range;
import com.db.mathservice.data.VariableConstraint;
import com.db.mathservice.utility.ArgumentSubstituter;
import com.db.mathservice.utility.DigitsFiller;
import com.db.mathservice.utility.SubstitutionResult;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Valentin on 07.09.2017.
 */
@Service("math.equation")
public class EquationGenerator implements ExerciseGenerator {
    private DigitsFiller digitsFiller;
    private ArgumentSubstituter argumentSubstituter;

    public EquationGenerator(DigitsFiller digitsFiller) {
        this.digitsFiller = digitsFiller;
    }

    @Autowired
    public EquationGenerator(DigitsFiller digitsFiller, ArgumentSubstituter argumentSubstituter) {
        this.digitsFiller = digitsFiller;
        this.argumentSubstituter = argumentSubstituter;
    }

    public EquationGenerator(ArgumentSubstituter argumentSubstituter) {
        this.argumentSubstituter = argumentSubstituter;
    }

    @Override
    public Equation generateExercise(ExerciseConfiguration exerciseConfiguration) {
        String template = exerciseConfiguration.getTemplate();
        Map<String, Range> rangeMap = exerciseConfiguration.getVariables().stream()
                .collect(Collectors.toMap(VariableConstraint::getVarName, o -> new Range(o.getFrom(), o.getTo())));
        template = digitsFiller.fillStringWithMissingDigits(template);

        Expression expression = new Expression(template);
        if (!expression.checkLexSyntax()) {
            throw new IllegalStateException(expression.getErrorMessage());
        }
        Expression equationExpression = argumentSubstituter.substituteArguments(expression, rangeMap, "x");
        String equationString = equationExpression.getExpressionString();
        SubstitutionResult substitutionResult = argumentSubstituter.substituteArgument(equationExpression, "x", rangeMap.get("x"));

        equationString += " = " +
                Integer.valueOf(Double.valueOf(substitutionResult.getExpression().calculate()).intValue()).toString();
        String answer = substitutionResult.getSubstitutedValue();

        return new Equation(equationString, answer);
    }
}
