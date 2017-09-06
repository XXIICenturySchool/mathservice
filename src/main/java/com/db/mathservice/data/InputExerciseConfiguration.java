package com.db.mathservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputExerciseConfiguration {
    private String expression;
    private int amount;
    private List<VariableConstraint> variables;
}
