package com.db.mathservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewExamTemplateController {
    @RequestMapping("/new_exam")
    public String newExam(@RequestParam(value="teacherId") int teacherId, Model model) {
        return "new_exam";
    }

}
