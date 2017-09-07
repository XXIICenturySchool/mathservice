package com.db.mathservice.business;

import com.db.mathservice.data.Equation;
import com.db.mathservice.data.ExerciseConfiguration;

/**
 * Created by Valentin on 07.09.2017.
 */
public interface EquationGenerator {

    Equation generateEquation(ExerciseConfiguration exerciseConfiguration);

}
