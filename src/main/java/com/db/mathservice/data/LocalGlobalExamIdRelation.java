package com.db.mathservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocalGlobalExamIdRelation {
    private String localId;
    private String globalId;
}
