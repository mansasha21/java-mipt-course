package com.client;

import com.parser.Parser;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HoldingCreator extends ClientCreator {
    @Override
    public Client createClient(FileReader reader) throws IOException {
        try {
            Parser parser = new Parser(reader);
            String name = parser.getValue(parser.parse(Parser.NAME_FIELD_PATTERN));
            Date date = new SimpleDateFormat("yyyy-MM-dd").
                    parse(parser.parse(Parser.DATE_PATTERN, parser.parse(Parser.DATE_FIELD_PATTERN)));
            Integer zip = Integer.decode(parser.parse(Parser.INT_PATTERN, parser.parse(Parser.ZIP_FIELD_PATTERN)));
            return new Holding(name, date, zip);

        } catch (ParseException e) {
            throw new IOException("smth wrong with date");
        }
    }
}
