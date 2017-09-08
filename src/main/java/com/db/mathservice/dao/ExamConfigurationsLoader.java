package com.db.mathservice.dao;

import com.db.mathservice.data.ExamConfiguration;
import com.db.mathservice.data.ExamConfigurationWithGlobalId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class ExamConfigurationsLoader {
    ExamConfigurationRepository examConfigurationRepository;
    LocalGlobalExamIdRelationRepository idRelationRepository;

    public List<ExamConfigurationWithGlobalId> loadConfigurationsWithGlobalId(String teacherId) {
        List<ExamConfigurationWithGlobalId> examConfigurationsWithGlobalId =
                new ArrayList<>();
        List<ExamConfiguration> examConfigurations = examConfigurationRepository.findByTeacherId(teacherId);
        for (ExamConfiguration examConfiguration : examConfigurations) {
            String localId = examConfiguration.getId();
            String globalId = idRelationRepository.findByLocalId(localId).getGlobalId();
            examConfigurationsWithGlobalId.add(
                    new ExamConfigurationWithGlobalId(examConfiguration, globalId));
        }

        return examConfigurationsWithGlobalId;
    }
}
