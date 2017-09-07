package com.db.mathservice.controllers;

import com.db.mathservice.data.ExamConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChooseExamTypeController {
    @RequestMapping("/choose_exam_type")
    public String chooseExamType(@RequestParam(value="teacherId") int teacherId) {
        return "choose_exam_type";
    }
}
