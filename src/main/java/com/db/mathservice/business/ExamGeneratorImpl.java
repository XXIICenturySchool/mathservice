package com.db.mathservice.business;

import com.db.mathservice.data.Exam;
import com.db.mathservice.data.ExamConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class ExamGeneratorImpl implements ExamGenerator {
    private Map<String, ExerciseGenerator> map;

    @Override
    public Exam generateExam(ExamConfiguration examConfiguration) {
        Exam.ExamBuilder examBuilder = Exam.builder()
                .teacherId(String.valueOf(examConfiguration.getTeacherId()));

        ExerciseGenerator exerciseGenerator = map.get(examConfiguration.getType());

        examConfiguration.getExerciseConfigurations()
                .forEach(exerciseConfiguration -> {
                    for (int i = 0; i < exerciseConfiguration.getAmount(); i++) {
                        examBuilder.task(exerciseGenerator.generateExercise(exerciseConfiguration));
                    }
                });

        return examBuilder.build();
    }
}
