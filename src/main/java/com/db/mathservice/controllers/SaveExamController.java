package com.db.mathservice.controllers;

import com.db.mathservice.dao.ExamConfigurationRepository;
import com.db.mathservice.data.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Valentin on 06.09.2017.
 */
@RestController
@AllArgsConstructor
public class SaveExamController {
    ExamConfigurationRepository repository;

    @PostMapping("/save_exam")
    public String addExam(@RequestBody InputExamConfiguration inputConfiguration) {
        ExamConfiguration.ExamConfigurationBuilder configurationBuilder = ExamConfiguration.builder()
                .title(inputConfiguration.getTitle())
                .teacherId(inputConfiguration.getTeacherId());

        for (InputExerciseConfiguration inputExerciseConfiguration : inputConfiguration.getInputConfigurations()) {
            ExerciseConfiguration.ExerciseConfigurationBuilder exerciseBuilder =
                    ExerciseConfiguration.builder().format(inputExerciseConfiguration.getExpression());

            for (VariableConstraint constraint : inputExerciseConfiguration.getVariables()) {
                exerciseBuilder.range(constraint.getVarName(), new Range(constraint.getFrom(), constraint.getTo()));
            }

            configurationBuilder.configuration(
                    new ExerciseConfigurationCounted(exerciseBuilder.build(), inputExerciseConfiguration.getAmount()));
        }

        repository.save(configurationBuilder.build());
        return "exams";
    }

}
