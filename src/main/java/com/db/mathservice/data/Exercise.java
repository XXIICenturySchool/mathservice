package com.db.mathservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Valentin on 07.09.2017.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public abstract class Exercise {
    protected String question;
    protected List<String> answer;
}
