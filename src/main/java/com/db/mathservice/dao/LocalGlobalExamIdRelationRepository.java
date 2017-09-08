package com.db.mathservice.dao;

import com.db.mathservice.data.LocalGlobalExamIdRelation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocalGlobalExamIdRelationRepository extends MongoRepository<LocalGlobalExamIdRelation, Integer> {
    LocalGlobalExamIdRelation findByLocalId(String localId);
}
