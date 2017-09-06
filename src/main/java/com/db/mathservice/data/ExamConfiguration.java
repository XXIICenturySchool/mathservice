package com.db.mathservice.data;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ExamConfiguration {
    @Id
    private int id;
    private int teacherId;
    private String title;
    @Singular
    private List<ExerciseConfigurationCounted> configurations;
}
