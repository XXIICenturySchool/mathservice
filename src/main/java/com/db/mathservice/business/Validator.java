package com.db.mathservice.business;

import com.db.mathservice.data.ExerciseConfiguration;

public interface Validator {

    boolean validate(ExerciseConfiguration configuration);

}