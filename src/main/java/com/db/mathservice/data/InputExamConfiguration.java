package com.db.mathservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputExamConfiguration {
    private String title;
    private List<InputExerciseConfiguration> inputConfigurations;
}
