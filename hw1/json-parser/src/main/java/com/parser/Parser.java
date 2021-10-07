package com.parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Parser {
    public static final String NAME_FIELD_PATTERN = "\"name\" *: *\".+\"";
    public static final String CLIENT_TYPE_FIELD_PATTERN = "\"clientType\" *: *\".+\"";
    public static final String TYPE_PATTERN = "INDIVIDUAL|LEGAL_ENTITY|HOLDING";
    public static final String SURNAME_FIELD_PATTERN = "\"surname\" *: *\".+\"";
    public static final String NUMBER_FIELD_PATTERN = "\"number\" *: *\\d+";
    public static final String INN_FIELD_PATTERN = "\"inn\" *: *\\d+";
    public static final String INT_PATTERN = "\\d+";
    public static final String STRING_PATTERN = "^\\w+( \\w+)*|[A-Яа-я]+( [A-Яа-я]+)*";
    public static final String ZIP_FIELD_PATTERN = "\"zip\" *: *\\d+";
    public static final String DATE_FIELD_PATTERN = "\"date\" *: *\"\\d{4}-\\d{2}-\\d{2}\"";
    public static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";

    private final Integer MAX_BUFFER_SIZE = 256;
    private final FileReader reader;
    private char[] buffer;
    private Boolean isRead;

    public Parser(FileReader reader) {
        this.reader = reader;
        this.buffer = new char[MAX_BUFFER_SIZE];
        this.isRead = Boolean.FALSE;
    }

    public String parse(String patternText) throws IOException {
        if (!isRead) {
            reader.read(buffer);
        }
        String jsonText = new String(buffer);
        return parse(patternText, jsonText);
    }

    public String parse(String patternText, String jsonText) throws IOException {
        Pattern pattern = Pattern.compile(patternText, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(jsonText);
        if (!matcher.find()) {
            throw new IOException("Cant parse string: " + jsonText);
        }
        return jsonText.substring(matcher.start(), matcher.end());
    }

    static public String determineClientType(FileReader reader) throws IOException {
        char[] tmpBuffer = new char[256];
        Pattern clientTypePattern = Pattern.compile(CLIENT_TYPE_FIELD_PATTERN);

        reader.read(tmpBuffer);
        String jsonText = new String(tmpBuffer);
        Matcher clientTypeMatcher = clientTypePattern.matcher(jsonText);
        if (!clientTypeMatcher.find()) {
            throw new IOException("No clientType field");
        }
        String clientTypeField = jsonText.substring(clientTypeMatcher.start(), clientTypeMatcher.end());
        Pattern typePattern = Pattern.compile(TYPE_PATTERN);
        Matcher typeMatcher = typePattern.matcher(clientTypeField);
        if (!typeMatcher.find()) {
            throw new IOException("Unsupported client type");
        }
        return clientTypeField.substring(typeMatcher.start(), typeMatcher.end());
    }

    public String getValue(String item) throws IOException {
        Pattern stringPattern = Pattern.compile(STRING_PATTERN);
        Matcher stringMatcher = stringPattern.matcher(item);
        if (!stringMatcher.find(item.indexOf(':'))) {
//            throw new IOException("Cant parse string as a value in string: " + item);
            return "";
        }

        return item.substring(stringMatcher.start(), stringMatcher.end());
    }

}
