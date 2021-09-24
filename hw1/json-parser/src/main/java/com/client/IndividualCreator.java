package com.client;

import com.parser.Parser;

import java.io.FileReader;
import java.io.IOException;


public class IndividualCreator extends ClientCreator {
    @Override
    public Client createClient(FileReader reader) throws IOException {
        Parser parser = new Parser(reader);
        String name = parser.getValue(parser.parse(Parser.NAME_FIELD_PATTERN));
        String surname = parser.getValue(parser.parse(Parser.SURNAME_FIELD_PATTERN));
        Integer number = Integer.decode(parser.parse(Parser.INT_PATTERN, parser.parse(Parser.NUMBER_FIELD_PATTERN)));
        return new Individual(name, surname, number);
    }
}
