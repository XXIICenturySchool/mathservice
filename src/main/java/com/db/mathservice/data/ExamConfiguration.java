package com.db.mathservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamConfiguration {
    private String title;
    private int teacherId;
    private String type;
    private List<ExerciseConfiguration> inputConfigurations;
}
