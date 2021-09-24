package com;

import static org.junit.jupiter.api.Assertions.*;

import com.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ParserTest {
    @Test
    public void stringValueTest() {
        Parser parser = new Parser(null);
        String emptyStringValue = "\"smth\": \"\"";
        String notEmptyStringValue = "\"smth\": \"lol\"";
        try {
            assertEquals("", parser.getValue(emptyStringValue));
        } catch (IOException e) {
            assertTrue(false);
            System.out.println("emptyStringValue error");
        }
        try {
            assertEquals("lol", parser.getValue(notEmptyStringValue));
        } catch (IOException e) {
            assertTrue(false);
            System.out.println("notEmptyStringValue error");
        }
    }

}
