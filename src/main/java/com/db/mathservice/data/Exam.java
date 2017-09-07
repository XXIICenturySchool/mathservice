package com.db.mathservice.data;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class Exam {
    @Id
    private String id;
    @Singular
    private List<Exercise> tasks;
    private String teacherId;
}
