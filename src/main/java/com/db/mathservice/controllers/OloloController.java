package com.db.mathservice.controllers;

import com.db.mathservice.data.ExerciseConfigurationCounted;
import com.db.mathservice.data.InputExerciseConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Valentin on 06.09.2017.
 */
@RestController
public class OloloController {

    @PostMapping("/ololo")
    public String printOlolo(@RequestBody InputExerciseConfiguration inputConfiguration) {
        System.out.println("inputConfiguration = " + inputConfiguration);
        return "ololo";
    }

}
