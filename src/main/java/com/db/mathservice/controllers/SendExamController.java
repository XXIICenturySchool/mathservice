package com.db.mathservice.controllers;

import com.db.mathservice.dao.ExamConfigurationRepository;
import com.db.mathservice.dao.ExamRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@AllArgsConstructor
public class SendExamController {
    ExamConfigurationRepository configurationRepository;
    ExamRepository examRepository;

    @RequestMapping
    private void sendExam(@RequestParam(value = "localExamId") int localExamId) {
//        configurationRepository.findOne()
//        configurationRepository.
//        return Exam;
    }
}
