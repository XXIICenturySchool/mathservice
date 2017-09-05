package com.db.mathservice.data;

import com.db.mathservice.data.Range;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class ExerciseConfiguration {
    private String format;
    @Singular
    private Map<String, Range> ranges;
}
