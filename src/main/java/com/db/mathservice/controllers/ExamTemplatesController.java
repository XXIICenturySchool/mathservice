package com.db.mathservice.controllers;

import com.db.mathservice.dao.ExamConfigurationRepository;
import com.db.mathservice.data.ExamConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@AllArgsConstructor
public class ExamTemplatesController {
    ExamConfigurationRepository repository;

    @RequestMapping("/exam_templates")
    public String templates(@RequestParam(value="teacherId") String teacherId, Model model) {
        List<ExamConfiguration> examConfigurations = repository.findByTeacherId(teacherId);
        model.addAttribute("examConfigurations", examConfigurations);
        return "exam_templates";
    }
}
