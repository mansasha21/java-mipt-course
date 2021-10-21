package ru.sber;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

public class CustomClassLoader extends ClassLoader {

    private URL[] urls;

    public CustomClassLoader(URL[] urls) {
        this.urls = urls;
    }

    @Override
    public Class findClass(String name) {
        byte[] b = new byte[0];
        try {
            for (URL url : urls) {
                b = loadClassFromURL(url, name);
                if (b == null) {
                    continue;
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromURL(URL url, String name) throws IOException {
        var urlCon = url.openConnection();
        var inputStream = urlCon.getInputStream();
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        StringBuilder nameBuilder = new StringBuilder();
        int i = 0;
        try {
            while ((nextValue = inputStream.read()) != -1) {
                if (i < name.length()) {
                    i += 1;
                    nameBuilder.append((char) (nextValue - 1));
                    continue;
                }
                if (!nameBuilder.toString().equals(name)) {
                    return null;
                }
                byteStream.write(nextValue - 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }


}
