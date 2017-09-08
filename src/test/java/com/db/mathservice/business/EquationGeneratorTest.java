package com.db.mathservice.business;

import com.db.mathservice.data.Equation;
import com.db.mathservice.data.ExerciseConfiguration;
import com.db.mathservice.data.VariableConstraint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EquationGeneratorTest {
    @Autowired
    EquationGenerator equationGenerator;

    @Test
    public void equationGeneratesRight() throws Exception {
        List<VariableConstraint> constraints = new ArrayList<>();
        constraints.add(new VariableConstraint("x", 2, 2));

        Equation equation = equationGenerator.generateExercise(
                new ExerciseConfiguration("x+3", 1, constraints));
        Assert.assertEquals(new Equation("x+3 = 5", "2"), equation);

        equation = equationGenerator.generateExercise(
                new ExerciseConfiguration("x*3+1", 1, constraints));
        Assert.assertEquals(new Equation("x*3+1 = 7", "2"), equation);

        equation = equationGenerator.generateExercise(
                new ExerciseConfiguration("x-5", 1, constraints));
        Assert.assertEquals(new Equation("x-5 = -3", "2"), equation);

    }


}
