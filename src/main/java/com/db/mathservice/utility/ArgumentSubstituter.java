package com.db.mathservice.utility;

import com.db.mathservice.data.Range;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.Map;

public interface ArgumentSubstituter {
    Expression substituteArguments(Expression expression, Map<String, Range> rangeMap);

    Expression substituteArguments(Expression expression, Map<String, Range> rangeMap, String ignoredVariable);

    SubstitutionResult substituteArgument(Expression expression, String variable, Range range);

}
