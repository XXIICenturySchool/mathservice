package com.db.mathservice.dao;

import com.db.mathservice.data.ExamConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExamConfigurationRepository extends MongoRepository<ExamConfiguration, Integer> {
    List<ExamConfiguration> findByTeacherId(String teacherId);
    ExamConfiguration findById(String teacherId);


}
