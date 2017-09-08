package com.db.mathservice.utility;

import com.db.mathservice.utility.DigitsFiller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DigitsFillerTest {
    @Autowired
    DigitsFiller digitsFiller;

    @Test
    public void fillStringWithMissingDigitsResultIsInteger() throws Exception {
        String s = digitsFiller.fillStringWithMissingDigits("1#34##5");
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            fail();
        }
    }

    @Test
    public void fillStringWithMissingDigitsResultHasSameLength() throws Exception {
        String s = digitsFiller.fillStringWithMissingDigits("1#34##5");

        assertEquals(s.length(), 7);
    }

    @Test
    public void fillStringWithMissingDigitsResultDoesNotChangeOtherDigits() throws Exception {
        String s = digitsFiller.fillStringWithMissingDigits("1#34##5");
        assertEquals(s.charAt(0), '1');
        assertEquals(s.charAt(2), '3');
        assertEquals(s.charAt(3), '4');
        assertEquals(s.charAt(6), '5');
    }

}