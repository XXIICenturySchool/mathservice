package com.db.mathservice.utility;

import com.db.mathservice.utility.TriangleBracketsPreprocessor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TriangleBracketsPreprocessorTest {
    @Autowired
    TriangleBracketsPreprocessor preprocessor;

    @Test
    public void process() throws Exception {
        Assert.assertEquals("1+5", preprocessor.process("1+<2+3>"));
        Assert.assertEquals("35/5", preprocessor.process("<35>/5"));
        Assert.assertEquals("10", preprocessor.process("<4+<2*3>>"));
        Assert.assertEquals("0", preprocessor.process("<<>>"));
    }

}