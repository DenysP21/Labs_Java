import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class PersonTest {

    static {
        System.setProperty("net.bytebuddy.experimental", "true");
    }

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(Person.class).verify();
    }
}