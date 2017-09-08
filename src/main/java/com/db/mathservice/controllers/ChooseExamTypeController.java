package com.db.mathservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChooseExamTypeController {
    @RequestMapping("/choose_exam_type")
    public String chooseExamType(@RequestParam(value="teacherId") int teacherId) {
        return "choose_exam_type";
    }
}
