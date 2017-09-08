package com.db.mathservice.business;

import com.db.mathservice.data.Exam;
import com.db.mathservice.data.ExamConfiguration;

public interface ExamGenerator {
    Exam generateExam(ExamConfiguration examConfiguration);
}
