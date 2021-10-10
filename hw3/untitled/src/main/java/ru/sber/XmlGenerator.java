package ru.sber;

public interface XmlGenerator<T> {
    public String toXml(T instance);
}
