package com.db.mathservice.controllers;

import com.db.mathservice.dao.ExamConfigurationRepository;
import com.db.mathservice.dao.LocalGlobalExamIdRelationRepository;
import com.db.mathservice.data.ExamConfiguration;
import com.db.mathservice.data.ExamConfigurationWithGlobalId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ExamTemplatesController {
    ExamConfigurationRepository examConfigurationRepository;
    LocalGlobalExamIdRelationRepository idRelationRepository;

    @RequestMapping("/exam_templates")
    public String templates(@RequestParam(value="teacherId") String teacherId, Model model) {
        List<ExamConfiguration> examConfigurations = examConfigurationRepository.findByTeacherId(teacherId);
        List<ExamConfigurationWithGlobalId> examConfigurationsWithGlobalId =
                new ArrayList<>();
        for (ExamConfiguration examConfiguration : examConfigurations) {
            String localId = examConfiguration.getId();
            String globalId = idRelationRepository.findByLocalId(localId).getGlobalId();
            examConfigurationsWithGlobalId.add(
                    new ExamConfigurationWithGlobalId(examConfiguration, globalId));
        }

        model.addAttribute("examConfigurationsWithGlobalId", examConfigurationsWithGlobalId);
        return "exam_templates";
    }
}
