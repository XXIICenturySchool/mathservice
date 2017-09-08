package com.db.mathservice.utility;

import com.db.mathservice.data.ExerciseConfiguration;
import com.db.mathservice.data.VariableConstraint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleArithmeticMinusValidator implements Validator {
    @Override
    public boolean validate(ExerciseConfiguration configuration) {
        List<VariableConstraint> variables = configuration.getVariables();
        return variables.get(0).getTo() > variables.get(1).getFrom();
    }
}
