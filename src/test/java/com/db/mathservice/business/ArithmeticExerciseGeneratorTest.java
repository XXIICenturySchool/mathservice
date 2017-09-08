package com.db.mathservice.business;

import com.db.mathservice.data.ExerciseConfiguration;
import com.db.mathservice.data.ArithmeticExercise;
import com.db.mathservice.data.VariableConstraint;
import com.db.mathservice.utility.DigitsFiller;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ArithmeticExerciseGeneratorTest {
    @Autowired
    ArithmeticExerciseGenerator exerciseGeneratorImpl;

    @Test
    public void exerciseWithoutSymbolsCalculatesRight() throws Exception {

        ArithmeticExercise arithmeticExercise =
                exerciseGeneratorImpl.generateExercise(new ExerciseConfiguration("3+2", 0,  new ArrayList<>()));

        Assert.assertEquals(arithmeticExercise, new ArithmeticExercise("3+2", "5"));
    }

    @Test(expected = IllegalStateException.class)
    public void exerciseWithNoLettersAndDigitsThrowsException() throws Exception {
        DigitsFiller mock = mock(DigitsFiller.class);
        when(mock.fillStringWithMissingDigits(Mockito.anyString())).thenReturn("..");

        ArithmeticExerciseGenerator exerciseGenerator = new ArithmeticExerciseGenerator(mock);

        exerciseGenerator.generateExercise(new ExerciseConfiguration("", 0, new ArrayList<>()));
    }

    @Test
    public void exerciseWithOneUnknownGeneratesAndCalculatesRight() throws Exception {
        List<VariableConstraint> constraints = new ArrayList<>();
        constraints.add(new VariableConstraint("a", 1, 1));
        ArithmeticExercise arithmeticExercise = exerciseGeneratorImpl.generateExercise(
                new ExerciseConfiguration("a+3", 1, constraints));

        Assert.assertEquals(arithmeticExercise, new ArithmeticExercise("1+3", "4"));
    }

    @Test
    public void exerciseWithTriangleBrackets() throws Exception {
        List<VariableConstraint> constraints = new ArrayList<>();
        constraints.add(new VariableConstraint("a", 2, 2));
        constraints.add(new VariableConstraint("b", 3, 3));
        constraints.add(new VariableConstraint("c", 3, 3));
        ArithmeticExercise arithmeticExercise = exerciseGeneratorImpl.generateExercise(
                new ExerciseConfiguration("<c + <a*b>>/b", 1, constraints));

        Assert.assertEquals(new ArithmeticExercise("9/3", "3"), arithmeticExercise);
    }

}