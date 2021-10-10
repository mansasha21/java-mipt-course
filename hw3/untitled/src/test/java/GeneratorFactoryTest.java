import org.junit.jupiter.api.Test;
import ru.sber.Person;
import ru.sber.XmlGeneratorFactory;

import java.lang.reflect.InvocationTargetException;

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

}
