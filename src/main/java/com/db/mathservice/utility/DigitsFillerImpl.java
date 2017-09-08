package com.db.mathservice.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Random;

@PropertySource("classpath:format.properties")
@Component
public class DigitsFillerImpl implements DigitsFiller {
    @Value("${fillingDigit}")
    private char fillingDigit;
    private final Random random = new Random(System.currentTimeMillis());

    @Override
    public String fillStringWithMissingDigits(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == fillingDigit) {
                chars[i] = Character.forDigit(random.nextInt(RADIX), RADIX);
            }
        }

        return new String(chars);
    }
}
