package com.db.mathservice.controllers;

import com.db.mathservice.data.ExerciseConfigurationCounted;
import com.db.mathservice.data.InputExamConfiguration;
import com.db.mathservice.data.InputExerciseConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Valentin on 06.09.2017.
 */
@RestController
public class SendExamController {

    @PostMapping("/send_exam")
    public String addExam(@RequestBody InputExamConfiguration inputConfiguration) {
        System.out.println("inputConfiguration = " + inputConfiguration);
        return "exams";
    }

}
