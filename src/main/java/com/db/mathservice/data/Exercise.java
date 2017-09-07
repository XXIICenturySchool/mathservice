package com.db.mathservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Valentin on 07.09.2017.
 */
@AllArgsConstructor
@Data
public abstract class Exercise {
    protected String type;
    protected String question;
    protected String answer;
}
