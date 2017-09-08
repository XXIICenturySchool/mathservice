package com.db.mathservice.business;

import com.db.mathservice.data.Range;
import lombok.RequiredArgsConstructor;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.parsertokens.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArgumentSubstituterWithValidation {
    private final Random random = new Random(System.currentTimeMillis());
    private TemplatePreprocessor preprocessor;

    @Autowired
    public ArgumentSubstituterWithValidation(TemplatePreprocessor preprocessor) {
        this.preprocessor = preprocessor;
    }

    private int getNumberBetween(int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException(String.format("Minimum must be less than minimum (min=%d, max=%d)", min, max));
        } else {
            return min + random.nextInt(max - min);
        }
    }

    public Expression substituteArguments(Expression expression, Map<String, Range> rangeMap) {
        List<Token> tokens = expression.getCopyOfInitialTokens();
        Set<String> arguments = getArguments(tokens);
        Map<String, String> argumentsRandomValuesMap = generateRandomValues(arguments, rangeMap);

        swapIfMinusExpressionReturnsNegative(tokens, argumentsRandomValuesMap);

        return generateExpressionWithRandomValues(tokens, argumentsRandomValuesMap);
    }

    private void swapIfMinusExpressionReturnsNegative(List<Token> tokens, Map<String, String> argumentsRandomValuesMap) {
        if (tokens.get(1).tokenStr.equals("-") &&
                Integer.parseInt(argumentsRandomValuesMap.get("a")) > Integer.parseInt(argumentsRandomValuesMap.get("b"))) {
            String swap = argumentsRandomValuesMap.get("a");
            argumentsRandomValuesMap.replace("a", argumentsRandomValuesMap.get("b"));
            argumentsRandomValuesMap.replace("b", swap);
        }
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

    private Set<String> getArguments(List<Token> tokens) {
        return tokens.stream()
                .filter(t -> t.keyWord.isEmpty())
                .filter(t -> t.looksLike.equals("argument"))
                .map(token -> token.tokenStr).collect(Collectors.toSet());
    }
}
