package com.db.mathservice.controllers;

import com.db.mathservice.communication.ExamRegistrar;
import com.db.mathservice.dao.ExamConfigurationRepository;
import com.db.mathservice.dao.LocalGlobalExamIdRelationRepository;
import com.db.mathservice.data.ExamConfiguration;
import com.db.mathservice.data.ExamCoordinates;
import com.db.mathservice.data.LocalGlobalExamIdRelation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Valentin on 06.09.2017.
 */
@RestController
@AllArgsConstructor
public class SaveExamController {
    private ExamRegistrar examRegistrar;
    private ExamConfigurationRepository repository;
    private LocalGlobalExamIdRelationRepository localGlobalExamIdRelationRepository;

    @SneakyThrows
    @PostMapping("/save_exam")
    public ExamCoordinates addExam(@RequestBody ExamConfiguration inputConfiguration) {
        String localId = repository.save(inputConfiguration).getId();

        ExamCoordinates examCoordinates = ExamCoordinates.builder().teacherId(inputConfiguration.getTeacherId())
                .url("/exams?localExamId=" + localId).build();

        String globalId = examRegistrar.registerExam(examCoordinates);

        localGlobalExamIdRelationRepository.save(new LocalGlobalExamIdRelation(localId, globalId));


        return examCoordinates;
    }

}
