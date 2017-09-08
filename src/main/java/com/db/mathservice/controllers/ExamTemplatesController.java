package com.db.mathservice.controllers;

import com.db.mathservice.dao.ExamConfigurationsLoader;
import com.db.mathservice.data.ExamConfigurationWithGlobalId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class ExamTemplatesController {
    ExamConfigurationsLoader examConfigurationsLoader;

    @RequestMapping("/")
    public String templates(@RequestParam(value="teacherId") String teacherId, Model model) {
        List<ExamConfigurationWithGlobalId> examConfigurationsWithGlobalId =
                examConfigurationsLoader.loadConfigurationsWithGlobalId(teacherId);

        model.addAttribute("examConfigurationsWithGlobalId", examConfigurationsWithGlobalId);
        return "exam_templates";
    }
}
