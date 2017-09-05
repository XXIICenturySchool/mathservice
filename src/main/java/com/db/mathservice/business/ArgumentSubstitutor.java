package com.db.mathservice.business;

import com.db.mathservice.data.Range;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.Map;

public interface ArgumentSubstitutor {
    Expression substituteArguments(Expression expression, Map<String, Range> rangeMap);
}
