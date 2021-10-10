import org.junit.jupiter.api.Test;
import ru.sber.Keyboard;
import ru.sber.Person;
import ru.sber.PlayList;
import ru.sber.XmlGeneratorFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorFactoryTest {
    @Test
    public void testClassWithPrimitiveFields() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var me = new Person(22, true, "Sasha");
        var factory = new XmlGeneratorFactory<Person>(Person.class);
        var generator = factory.createGenerator();
        String xml = generator.toXml(me);
        String expected = "<Person>\n" +
                "\t<age>22<\\age>\n" +
                "\t<alive>true<\\alive>\n" +
                "\t<name>Sasha<\\name>\n" +
                "<\\Person>";
        assertEquals(expected, xml);

    }

    @Test
    public void testClassWithArrayField() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Integer[] keys = {3, 2, 1, 6};
        var keyboard = new Keyboard(keys, 9);
        var factory = new XmlGeneratorFactory<Keyboard>(Keyboard.class);
        var generator = factory.createGenerator();
        String xml = generator.toXml(keyboard);
        String expected = "<Keyboard>\n" +
                "\t<keys>[3, 2, 1, 6]<\\keys>\n" +
                "\t<id>9<\\id>\n" +
                "<\\Keyboard>";
        assertEquals(expected, xml);

    }

    @Test
    public void testClassWithCollectionField() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var playList = new PlayList(Set.of("NF", "Anacondaz", "Loqie"), 2L);
        var factory = new XmlGeneratorFactory<PlayList>(PlayList.class);
        var generator = factory.createGenerator();
        String xml = generator.toXml(playList);
        String expected = "<PlayList>\n" +
                "\t<data>[NF, Loqie, Anacondaz]<\\data>\n" +
                "\t<userId>2<\\userId>\n" +
                "<\\PlayList>";
        assertEquals(expected, xml);

    }

}
