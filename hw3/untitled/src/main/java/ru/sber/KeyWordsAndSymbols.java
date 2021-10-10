package ru.sber;

public enum KeyWordsAndSymbols {
    CLASS(" class "),
    IMPLEMENTS(" implements "),
    OVERRIDE("@Override\n"),
    PUBLIC(" public "),
    APPEND("append"),
    STRING(" String "),
    GENERIC(" <T> "),
    XML_GENERATOR(" XmlGenerator<T> "),
    TO_XML_STRING_FUNCTION("toXml(T instance) {\n\t"),
    NEXT_LINE("\n"),
    TAB_SYM("\t"),
    OPEN_PARENTHESIS("{"),
    CLOSE_PARENTHESIS("}"),
    OPEN_PARENTHESIS_CIRCLE("("),
    CLOSE_PARENTHESIS_CIRCLE(")"),
    SPACE_SYM(" "),
    ASSIGN_SYM(" = ");


    private final String key;

    public String getString() {
        return this.key;
    }

    KeyWordsAndSymbols(String key) {
        this.key = key;
    }
}
