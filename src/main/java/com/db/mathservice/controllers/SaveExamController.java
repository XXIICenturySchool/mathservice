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
    public ExamCoordinates addExam(@RequestBody ExamConfiguration inputConfiguration) {
        String id = repository.save(inputConfiguration).getId();
        //TODO register exam
        return ExamCoordinates.builder().teacherId(inputConfiguration.getTeacherId())
                .url("/exams?localExamId=" + id).build();
    }

}
