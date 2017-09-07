package com.db.mathservice.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.mariuszgromada.math.mxparser.Expression;

/**
 * Created by Valentin on 07.09.2017.
 */
@Data
@AllArgsConstructor
public class SubstitutionResult {
    private Expression expression;
    private String substitutedValue;
}
