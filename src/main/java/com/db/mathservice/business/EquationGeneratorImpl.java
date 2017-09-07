package com.db.mathservice.business;

import com.db.mathservice.data.Equation;
import com.db.mathservice.data.ExerciseConfiguration;
import com.db.mathservice.data.Range;
import com.db.mathservice.data.VariableConstraint;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Valentin on 07.09.2017.
 */
public class EquationGeneratorImpl implements EquationGenerator {
    private DigitsFiller digitsFiller;
    private ArgumentSubstituter argumentSubstituter;

    public EquationGeneratorImpl(DigitsFiller digitsFiller) {
        this.digitsFiller = digitsFiller;
    }

    @Autowired
    public EquationGeneratorImpl(DigitsFiller digitsFiller, ArgumentSubstituter argumentSubstituter) {
        this.digitsFiller = digitsFiller;
        this.argumentSubstituter = argumentSubstituter;
    }

    public EquationGeneratorImpl(ArgumentSubstituter argumentSubstituter) {
        this.argumentSubstituter = argumentSubstituter;
    }

    @Override
    public Equation generateEquation(ExerciseConfiguration exerciseConfiguration) {
        String template = exerciseConfiguration.getTemplate();
        Map<String, Range> rangeMap = exerciseConfiguration.getVariables().stream()
                .collect(Collectors.toMap(VariableConstraint::getVarName, o -> new Range(o.getFrom(), o.getTo())));
        template = digitsFiller.fillStringWithMissingDigits(template);

        Expression expression = new Expression(template);
        if (!expression.checkLexSyntax()) {
            throw new IllegalStateException(expression.getErrorMessage());
        }
        Expression equationExpression = argumentSubstituter.substituteArguments(expression, rangeMap, "x");
        String equationString = equationExpression.toString();
        SubstitutionResult substitutionResult = argumentSubstituter.substituteArgument(equationExpression, "x", rangeMap.get("x"));

        equationString += " = "+substitutionResult.getExpression().calculate();
        String answer = substitutionResult.getSubstitutedValue();

        return new Equation(equationString, answer);
    }
}
