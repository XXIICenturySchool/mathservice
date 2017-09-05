package com.db.mathservice.dao;

import com.db.mathservice.data.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepository extends MongoRepository<Exam, Integer> {
}
