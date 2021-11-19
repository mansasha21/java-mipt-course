package ru.sber;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class CustomClassLoader extends ClassLoader {

    private Map<String, byte[]> preparedClasses;

    public CustomClassLoader(Map<String, URL> nameUrlMap) {
        preparedClasses = new HashMap<>();
        for (String name : nameUrlMap.keySet()) {
            preparedClasses.put(name, loadClassFromURL(nameUrlMap.get(name), name));
        }
    }

    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        var b = preparedClasses.getOrDefault(name, null);

        if (b == null) {
            throw new ClassNotFoundException();
        }

        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromURL(URL url, String name) {
        URLConnection urlCon = null;
        InputStream inputStream = null;
        try {
            urlCon = url.openConnection();
            inputStream = urlCon.getInputStream();
        } catch (IOException e) {
            return null;
        }

        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        int nextValue;
        StringBuilder nameBuilder = new StringBuilder();

        try {
            while ((nextValue = inputStream.read()) != -1) {
                if (nameBuilder.length() < name.length()) nameBuilder.append(decodeChar(nextValue));
                else byteStream.write(nextValue - 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        buffer = byteStream.toByteArray();

        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            byteStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    private char decodeChar(int b) {
        return (char) (b - 1);
    }

}
