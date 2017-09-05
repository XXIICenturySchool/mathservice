package com.db.mathservice.business;

import com.db.mathservice.data.Range;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.parsertokens.Token;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class ArgumentSubstitutorImpl implements ArgumentSubstitutor {
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
}
