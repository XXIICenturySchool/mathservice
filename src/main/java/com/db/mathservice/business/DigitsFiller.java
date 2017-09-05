package com.db.mathservice.business;

public interface DigitsFiller {
    int RADIX = 10;

    String fillStringWithMissingDigits(String str);
}
