package com.db.mathservice;

import com.db.mathservice.business.DigitsFiller;
import com.db.mathservice.data.ExerciseConfiguration;
import com.db.mathservice.business.ExerciseGeneratorImpl;
import com.db.mathservice.data.Exercise;
import com.db.mathservice.data.Range;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExerciseGeneratorImplTest {
    @Autowired
    ExerciseGeneratorImpl exerciseGeneratorImpl;
    @Test
    public void exerciseWithoutSymbolsCalculatesRight() throws Exception {

        Exercise exercise = exerciseGeneratorImpl.generateExercise(new ExerciseConfiguration("3+2", null));

        Assert.assertEquals(exercise, new Exercise("3+2", "5"));
    }

    @Test(expected = IllegalStateException.class)
    public void exerciseWithNoLettersAndDigitsThrowsException() throws Exception {
        DigitsFiller mock = mock(DigitsFiller.class);
        when(mock.fillStringWithMissingDigits(Mockito.anyString())).thenReturn("..");

        ExerciseGeneratorImpl exerciseGenerator = new ExerciseGeneratorImpl(mock);

        Exercise exercise = exerciseGenerator.generateExercise(new ExerciseConfiguration("", null));
    }

    @Test
    public void exerciseWithOneUnknownGeneratesAndCalculatesRight() throws Exception {
        Map<String, Range> rangeMap = new HashMap<>();
        rangeMap.put("a", new Range(1, 1));
        Exercise exercise = exerciseGeneratorImpl.generateExercise(
                new ExerciseConfiguration("a+3", rangeMap));

        Assert.assertEquals(exercise, new Exercise("1+3", "4"));
    }

}