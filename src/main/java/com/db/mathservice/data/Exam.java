package com.db.mathservice.data;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class Exam {
    private int id;
    @Singular
    private List<Exercise> tasks;
    private String teacherId;
}
