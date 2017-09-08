package com.db.mathservice.communication;

import com.db.mathservice.data.ExamCoordinates;
import lombok.SneakyThrows;

public interface ExamRegistrar {
    @SneakyThrows
    String registerExam(ExamCoordinates examCoordinates);
}
