package com.db.mathservice.business;

import com.db.mathservice.data.Range;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.parsertokens.Token;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ArgumentSubstituterImpl implements ArgumentSubstituter {
    private final Random random = new Random(System.currentTimeMillis());

    private int getNumberBetween(int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException(String.format("Minimum must be less than minimum (min=%d, max=%d)", min, max));
        } else {
            return min + random.nextInt(max - min);
        }
    }

    @Override
    public Expression substituteArguments(Expression expression, Map<String, Range> rangeMap) {
        List<Token> tokens = expression.getCopyOfInitialTokens();
        tokens.stream()
                .filter(t -> t.keyWord.isEmpty())
                .filter(t -> t.looksLike.equals("argument"))
                .forEach(t -> {
                    Range range = rangeMap.get(t.tokenStr);
                    t.tokenStr = Integer.valueOf(getNumberBetween(range.getMin(), range.getMax() + 1)).toString();
                });

        return new Expression(tokens.stream()
                .map(t -> t.tokenStr)
                .collect(Collectors.joining()));
    }
/*
    @Override
    public Expression substituteArguments(Expression expression, Map<String, Range> rangeMap, String ignoredVariable) {
        List<Token> tokens = expression.getCopyOfInitialTokens();
        tokens.stream()
                .filter(t -> t.keyWord.isEmpty())
                .filter(t -> t.looksLike.equals("argument"))
                .filter(t -> !t.tokenStr.equals(ignoredVariable))
                .forEach(t -> {
                    Range range = rangeMap.get(t.tokenStr);
                    t.tokenStr = Integer.valueOf(getNumberBetween(range.getMin(), range.getMax() + 1)).toString();
                });

        return new Expression(tokens.stream()
                .map(t -> t.tokenStr)
                .collect(Collectors.joining()));
    }*/
    @Override
    public Expression substituteArguments(Expression expression, Map<String, Range> rangeMap, String ignoredVariable) {
        List<Token> tokens = expression.getCopyOfInitialTokens();
        Set<String> arguments = tokens.stream()
                .filter(t -> t.keyWord.isEmpty())
                .filter(t -> t.looksLike.equals("argument"))
                .filter(t -> !t.tokenStr.equals(ignoredVariable))
                .map(token -> token.tokenStr).collect(Collectors.toSet());

        Map<String, String> argumentsRandomValuesMap = arguments.stream()
                .collect(Collectors.toMap(t -> t,
                        t -> Integer.valueOf(getNumberBetween(rangeMap.get(t).getMin(), rangeMap.get(t).getMax() + 1))
                                .toString()));

        tokens.stream()
                .filter(t -> argumentsRandomValuesMap.containsKey(t.tokenStr))
                .forEach(t -> t.tokenStr = argumentsRandomValuesMap.get(t.tokenStr));

        return new Expression(tokens.stream()
                .map(t -> t.tokenStr)
                .collect(Collectors.joining()));
    }

    @Override
    public SubstitutionResult substituteArgument(Expression expression, String variable, Range range) {
        String randomValue = Integer.valueOf(getNumberBetween(range.getMin(), range.getMax() + 1)).toString();
        List<Token> tokens = expression.getCopyOfInitialTokens();

        tokens.stream()
                .filter(t -> t.tokenStr.equals(variable))
                .forEach(t -> t.tokenStr = randomValue);

        return new SubstitutionResult(new Expression(tokens.stream()
                .map(t -> t.tokenStr)
                .collect(Collectors.joining())), randomValue);
    }
}
