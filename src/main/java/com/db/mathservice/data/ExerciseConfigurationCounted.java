package com.db.mathservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseConfigurationCounted {
    private ExerciseConfiguration exerciseConfiguration;
    private int count;
}
