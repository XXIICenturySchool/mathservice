package com.db.mathservice.data;

import java.util.ArrayList;
import java.util.List;

public class Equation extends Exercise {
    public Equation(String question, String answer) {
        List<String> a = new ArrayList<>();
        a.add(answer);
        this.question = question;
        this.answer = a;
    }
}
