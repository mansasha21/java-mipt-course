import ru.sber.CustomClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MalformedURLException {

        String[] names = {
                "ru.sbt.java.tasks.Secret",
                "ru.sbt.java.tasks.VeryStrangeSecret"
        };
        URL[] urls = {
                new URL("https://www.googleapis.com/drive/v3/files/1Hda8Kn9m96LBiNDUvBP7gTmrLustg70H?alt=media&key=AIzaSyCnyt2lgtvTEVvITi-mD7v0s49OaxLcEog"),
                new URL("https://www.googleapis.com/drive/v3/files/1wG0bcva7AcA2v2TUEADYJFWCgSyL7KzN?alt=media&key=AIzaSyCnyt2lgtvTEVvITi-mD7v0s49OaxLcEog")
        };

        Map<String, URL> nameUrlMap = new HashMap<>();
        for (int i = 0; i < urls.length; i++) {
            nameUrlMap.put(names[i], urls[i]);

        }
        CustomClassLoader loader = new CustomClassLoader(nameUrlMap);
        for (String name : names) {
            ((Runnable) loader.loadClass(name).getConstructor().newInstance()).run();
        }
    }
}
