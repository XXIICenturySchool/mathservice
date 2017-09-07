package com.db.mathservice.controllers;

import com.db.mathservice.dao.ExamConfigurationRepository;
import com.db.mathservice.data.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Valentin on 06.09.2017.
 */
@RestController
@AllArgsConstructor
public class SaveExamController {
    ExamConfigurationRepository repository;

    @PostMapping("/save_exam")
    public String addExam(@RequestBody ExamConfiguration inputConfiguration) {
        repository.save(inputConfiguration);
        //TODO register exam
        return "exams";
    }

}
