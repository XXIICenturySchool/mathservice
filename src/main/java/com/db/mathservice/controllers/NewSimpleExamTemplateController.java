package com.db.mathservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewSimpleExamTemplateController {
    @RequestMapping("/new_simple_exam")
    public String newSimpleExam(@RequestParam(value="teacherId") int teacherId, Model model) {
        return "new_simple_exam";
    }

}
