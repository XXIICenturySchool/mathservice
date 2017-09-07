package com.db.mathservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamConfiguration {
    @Id
    private String id;
    private String title;
    private int teacherId;
    private String type;
    private List<ExerciseConfiguration> inputConfigurations;
}
