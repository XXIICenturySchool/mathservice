package com.db.mathservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamConfigurationWithGlobalId {
    private ExamConfiguration examConfiguration;
    private String globalId;
}
