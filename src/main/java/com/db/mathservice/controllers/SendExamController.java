package com.db.mathservice.controllers;

import com.db.mathservice.business.ExamGenerator;
import com.db.mathservice.dao.ExamConfigurationRepository;
import com.db.mathservice.dao.ExamRepository;
import com.db.mathservice.data.Exam;
import com.db.mathservice.data.ExamConfiguration;
import com.db.mathservice.data.ExamToSend;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SendExamController {
    private ExamConfigurationRepository configurationRepository;
    private ExamRepository examRepository;
    private ExamGenerator examGenerator;

    @GetMapping("/exams")
    private ExamToSend sendExam(@RequestParam(value = "localExamId") String localExamId) {
        ExamConfiguration examConfiguration = configurationRepository.findById(localExamId);
        Exam exam = examGenerator.generateExam(examConfiguration);
        examRepository.save(exam);
        return ExamToSend.builder().tasks(exam.getTasks())
                .teacherId(exam.getTeacherId()).build();
    }
}
