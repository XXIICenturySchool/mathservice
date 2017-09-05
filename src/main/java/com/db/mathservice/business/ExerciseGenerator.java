package com.db.mathservice.business;

import com.db.mathservice.data.Exercise;
import com.db.mathservice.data.ExerciseConfiguration;

public interface ExerciseGenerator {
    Exercise generateExercise(ExerciseConfiguration exerciseConfiguration);
}
