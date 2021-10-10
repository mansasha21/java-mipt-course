package ru.sber;

import java.lang.reflect.InvocationTargetException;

public interface GeneratorFactory<T> {
    XmlGenerator<T> createGenerator() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
