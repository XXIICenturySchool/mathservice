package com.db.mathservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseConfiguration {
    private String template;
    private int amount;
    private List<VariableConstraint> variables;
}
