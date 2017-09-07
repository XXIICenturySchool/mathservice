package com.db.mathservice.business;

import com.db.mathservice.data.ArithmeticExercise;
import com.db.mathservice.data.ExerciseConfiguration;

public interface ArithmeticExerciseGenerator {
    ArithmeticExercise generateArithmeticExercise(ExerciseConfiguration exerciseConfiguration);
}
