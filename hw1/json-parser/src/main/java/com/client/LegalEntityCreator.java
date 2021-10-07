package com.client;

import com.parser.Parser;

import java.io.FileReader;
import java.io.IOException;

public class LegalEntityCreator extends ClientCreator {
    @Override
    public Client createClient(FileReader reader) throws IOException {
        Parser parser = new Parser(reader);
        String name = parser.getValue(parser.parse(Parser.NAME_FIELD_PATTERN));
        Integer inn = Integer.decode(parser.parse(Parser.INT_PATTERN, parser.parse(Parser.INN_FIELD_PATTERN)));
        return new LegalEntity(name, inn);
    }
}
