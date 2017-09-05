package com.db.mathservice.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Exercise {
    private String question;
    private final String type = "math.arithmetic";
    private String answer;
}
