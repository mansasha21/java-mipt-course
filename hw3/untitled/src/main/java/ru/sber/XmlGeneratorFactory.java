package ru.sber;

import com.google.common.primitives.Primitives;
import net.openhft.compiler.CompilerUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

public class XmlGeneratorFactory<T> implements GeneratorFactory<T> {
    private final String PACKAGE_IMPORT_STRING = "package ru.sber;\n";
    private final Class<?> clazz;
    private final StringBuilder xmlStringBuilder;
    private final String className;

    public XmlGeneratorFactory(Class<?> clazz) {
        this.clazz = clazz;
        this.xmlStringBuilder = new StringBuilder();
        this.className = clazz.getName().substring(clazz.getName().lastIndexOf('.') + 1);
    }


    @Override
    public XmlGenerator<T> createGenerator() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {


        String className = clazz.getName().substring(clazz.getName().lastIndexOf('.') + 1);
        Field[] declaredFields = getDeclaredFields();
        String xmlGeneratorName = className + "XmlGenerator";
        addHeader(className, xmlGeneratorName);
        addBody(declaredFields);
        addTail(className);
        System.out.println(xmlStringBuilder.toString());
        Class generatorClass = CompilerUtils.CACHED_COMPILER.loadFromJava("ru.sber." + xmlGeneratorName,
                xmlStringBuilder.toString());
        return (XmlGenerator<T>) generatorClass.getDeclaredConstructor().newInstance();

    }

    private void addHeader(String className, String generatorName) {
        xmlStringBuilder.append(PACKAGE_IMPORT_STRING)
                .append(KeyWordsAndSymbols.PUBLIC.getString())
                .append(KeyWordsAndSymbols.CLASS.getString())
                .append(generatorName)
                .append(KeyWordsAndSymbols.GENERIC.getString())
                .append(KeyWordsAndSymbols.IMPLEMENTS.getString())
                .append(KeyWordsAndSymbols.XML_GENERATOR.getString())
                .append(KeyWordsAndSymbols.OPEN_PARENTHESIS.getString())
                .append(KeyWordsAndSymbols.NEXT_LINE.getString())
                .append(KeyWordsAndSymbols.OVERRIDE.getString())
                .append(KeyWordsAndSymbols.PUBLIC.getString())
                .append(KeyWordsAndSymbols.STRING.getString())
                .append(KeyWordsAndSymbols.TO_XML_STRING_FUNCTION.getString())
                .append("StringBuilder xmlBuilder = new StringBuilder();")
                .append(KeyWordsAndSymbols.NEXT_LINE.getString())
                .append(KeyWordsAndSymbols.TAB_SYM.getString())
                .append(className)
                .append(KeyWordsAndSymbols.SPACE_SYM.getString())
                .append(className.toLowerCase().charAt(0) + className.substring(1))
                .append(KeyWordsAndSymbols.ASSIGN_SYM.getString())
                .append(KeyWordsAndSymbols.OPEN_PARENTHESIS_CIRCLE.getString())
                .append(className)
                .append(KeyWordsAndSymbols.CLOSE_PARENTHESIS_CIRCLE.getString())
                .append(" instance;")
                .append(KeyWordsAndSymbols.NEXT_LINE.getString())
                .append(KeyWordsAndSymbols.TAB_SYM.getString())
                .append("xmlBuilder.append(\"<" + className + ">\")");
    }

    private void addBody(Field[] declaredFields) {
        Integer curInnerLevel = 1;
        xmlStringBuilder.append(KeyWordsAndSymbols.NEXT_LINE.getString());
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName().substring(declaredField.getName().lastIndexOf('.') + 1);
            String getterName = getGetter(fieldName);
            if (getterName == null) {
                continue;
            }
            Class<?> fieldType = declaredField.getType();
            if (isFieldPrimitive(fieldType) || fieldType == String.class) {
                xmlStringBuilder.append(KeyWordsAndSymbols.TAB_SYM.getString())
                        .append(".")
                        .append(KeyWordsAndSymbols.APPEND.getString())
                        .append(KeyWordsAndSymbols.OPEN_PARENTHESIS_CIRCLE.getString())
                        .append(constructPrimitiveField(fieldName, curInnerLevel, getterName))
                        .append(KeyWordsAndSymbols.CLOSE_PARENTHESIS_CIRCLE.getString())
                        .append(KeyWordsAndSymbols.NEXT_LINE.getString());
            }

        }
    }

    private void addTail(String className) {
        xmlStringBuilder.append(KeyWordsAndSymbols.TAB_SYM.getString())
                .append(".")
                .append(KeyWordsAndSymbols.APPEND.getString())
                .append(KeyWordsAndSymbols.OPEN_PARENTHESIS_CIRCLE.getString())
                .append("\"\\n<\\\\" + className + ">\"")
                .append(KeyWordsAndSymbols.CLOSE_PARENTHESIS_CIRCLE.getString())
                .append(";")
                .append(KeyWordsAndSymbols.NEXT_LINE.getString())
                .append(KeyWordsAndSymbols.TAB_SYM.getString())
                .append("return xmlBuilder.toString();")
                .append(KeyWordsAndSymbols.NEXT_LINE.getString())
                .append(KeyWordsAndSymbols.CLOSE_PARENTHESIS.getString())
                .append(KeyWordsAndSymbols.NEXT_LINE.getString())
                .append(KeyWordsAndSymbols.CLOSE_PARENTHESIS.getString())
                .append(KeyWordsAndSymbols.NEXT_LINE.getString())
                .append(KeyWordsAndSymbols.TAB_SYM.getString());

    }

    private String getGetter(String fieldName) {
        var rawMethods = clazz.getMethods();
        var methods = new HashSet<String>();
        for (Method method : rawMethods) {
            methods.add(method.getName().substring(method.getName().lastIndexOf(".") + 1));
        }
        var lowerName = fieldName.toUpperCase().charAt(0) + fieldName.substring(1);
        if (methods.contains("get" + lowerName)) {
            return "get" + lowerName;
        } else if (methods.contains("is" + lowerName)) {
            return "is" + lowerName;
        }
        return null;

    }

    private Boolean isFieldPrimitive(Class<?> fieldClass) {
        return Primitives.isWrapperType(fieldClass);
    }

    private Field[] getDeclaredFields() {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
        }
        return declaredFields;
    }

    private String constructPrimitiveField(String name, Integer innerLevel, String getterName) {
        StringBuilder tagBuilder = new StringBuilder();
        tagBuilder.append("\"");
        tagBuilder.append("\\n");

        for (int i = 0; i < innerLevel; i++) {
            tagBuilder.append(KeyWordsAndSymbols.TAB_SYM.getString());
        }
        var classNameLower = this.className.toLowerCase().charAt(0) + this.className.substring(1);
        tagBuilder.append("<" + name + ">\" + " + classNameLower + "." + getterName + "()" + " + \"<\\\\" + name + ">\"");
        return tagBuilder.toString();
    }
}