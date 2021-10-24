import model.Reader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReaderTest {
    @Test
    public void testName() {
        Reader reader = new Reader();
        reader.setName("Raiymbek");
        assertTrue(reader.getName() == "Raiymbek");
    }

    @Test
    public void testSurname() {
        Reader reader = new Reader();
        reader.setSurname("Zhaksylyk");
        assertTrue(reader.getSurname() == "Zhaksylyk");
    }

    @Test
    public void testEmail() {
        Reader reader = new Reader();
        reader.setEmail("raiymbekz_02@mail.ru");
        assertTrue(reader.getEmail() == "raiymbekz_02@mail.ru");
    }



}
