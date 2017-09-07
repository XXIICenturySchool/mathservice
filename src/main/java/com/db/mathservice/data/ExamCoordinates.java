package com.db.mathservice.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
public class ExamCoordinates {
    private String teacherId;
    private String url;
    private final String serviceName = "MathService";
}
