package com.db.mathservice.utility;

import com.db.mathservice.data.Range;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.parsertokens.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ArgumentSubstituterImpl implements ArgumentSubstituter {
    private final Random random = new Random(System.currentTimeMillis());
    private TemplatePreprocessor preprocessor;

    @Autowired
    public ArgumentSubstituterImpl(TemplatePreprocessor preprocessor) {
        this.preprocessor = preprocessor;
    }

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
        Set<String> arguments = getArguments(tokens);
        Map<String, String> argumentsRandomValuesMap = generateRandomValues(arguments, rangeMap);
        return generateExpressionWithRandomValues(tokens, argumentsRandomValuesMap);
    }

    @Override
    public Expression substituteArguments(Expression expression, Map<String, Range> rangeMap, String ignoredVariable) {
        List<Token> tokens = expression.getCopyOfInitialTokens();
        Set<String> arguments = getArguments(tokens);
        arguments = ignoreVariable(arguments, ignoredVariable);
        Map<String, String> argumentsRandomValuesMap = generateRandomValues(arguments, rangeMap);
        return generateExpressionWithRandomValues(tokens, argumentsRandomValuesMap);
    }

    @Override
    public SubstitutionResult substituteArgument(Expression expression, String variable, Range range) {
        String randomValue = Integer.valueOf(getNumberBetween(range.getMin(), range.getMax() + 1)).toString();

        List<Token> tokens = expression.getCopyOfInitialTokens();
        Set<String> arguments = getArguments(tokens);

        tokens.stream()
                .filter(t -> t.tokenStr.equals(variable))
                .forEach(t -> t.tokenStr = randomValue);

        String expressionString = tokens.stream().map(token -> token.tokenStr).collect(Collectors.joining());
        if (arguments.size() == 1) {
            expressionString = preprocessor.process(expressionString);
        }

        return new SubstitutionResult(new Expression(expressionString), randomValue);
    }

    private Expression generateExpressionWithRandomValues(List<Token> tokens, Map<String, String> argumentsRandomValuesMap) {
        tokens.stream().filter(t -> argumentsRandomValuesMap.containsKey(t.tokenStr))
                .forEach(t -> t.tokenStr = argumentsRandomValuesMap.get(t.tokenStr));

        String expressionString = tokens.stream().map(token -> token.tokenStr).collect(Collectors.joining());
        expressionString = preprocessor.process(expressionString);

        return new Expression(expressionString);
    }

    private Map<String, String> generateRandomValues(Set<String> arguments, Map<String, Range> rangeMap) {
        return arguments.stream()
                .collect(Collectors.toMap(t -> t,
                        t -> Integer.valueOf(getNumberBetween(rangeMap.get(t).getMin(), rangeMap.get(t).getMax() + 1))
                                .toString()));
    }

    private Set<String> ignoreVariable(Set<String> arguments, String ignoredVariable) {
        return arguments.stream()
                .filter(arg -> !arg.equals(ignoredVariable)).collect(Collectors.toSet());
    }

    private Set<String> getArguments(List<Token> tokens) {
        return tokens.stream()
                .filter(t -> t.keyWord.isEmpty())
                .filter(t -> t.looksLike.equals("argument"))
                .map(token -> token.tokenStr).collect(Collectors.toSet());
    }
}
