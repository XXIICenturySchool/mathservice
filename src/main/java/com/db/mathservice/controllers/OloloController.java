package com.db.mathservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by Valentin on 06.09.2017.
 */
@Controller
public class OloloController {

    @PostMapping("/ololo")
    public String printOlolo(HttpServletRequest request) {
        return "ololo";
    }

}
