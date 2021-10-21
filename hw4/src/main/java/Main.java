import ru.sber.CustomClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MalformedURLException {
        URL[] urls = {
                new URL("https://www.googleapis.com/drive/v3/files/1Hda8Kn9m96LBiNDUvBP7gTmrLustg70H?alt=media&key=AIzaSyCnyt2lgtvTEVvITi-mD7v0s49OaxLcEog"),
                new URL("https://www.googleapis.com/drive/v3/files/1wG0bcva7AcA2v2TUEADYJFWCgSyL7KzN?alt=media&key=AIzaSyCnyt2lgtvTEVvITi-mD7v0s49OaxLcEog")
        };
        CustomClassLoader loader = new CustomClassLoader(urls);
        ((Runnable) loader.loadClass("ru.sbt.java.tasks.Secret").getConstructor().newInstance()).run();
        ((Runnable) loader.loadClass("ru.sbt.java.tasks.VeryStrangeSecret").getConstructor().newInstance()).run();
    }
}
