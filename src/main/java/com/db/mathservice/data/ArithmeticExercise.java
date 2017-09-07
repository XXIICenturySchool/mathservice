package com.db.mathservice.data;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticExercise extends Exercise {
    public ArithmeticExercise(String question, String answer) {
        List<String> a = new ArrayList<>();
        a.add(answer);
        this.type = "math.arithmetic";
        this.question = question;
        this.answer = a;
    }
}
