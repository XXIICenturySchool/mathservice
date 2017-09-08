package com.db.mathservice.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExamCoordinates {
    private String teacherId;
    private String url;
    private final String serviceName = "mathservice";
}
