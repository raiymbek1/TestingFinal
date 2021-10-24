import model.Book;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTest {
/*
    @Test
    public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Book book = new Book();

        //when
        book.setName("Qara Soz");



        //then
        final Field field = book.getClass().getDeclaredField("Qara Soz");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(book), "Qara Soz");
    }

    @Test
    public void testGetter_getsValue() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Book book = new Book();
        final Field field = book.getClass().getDeclaredField("Qara Soz");
        field.setAccessible(true);
        field.set(book, "magic_values");

        //when
        final String result = book.getName();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }
*/

    @Test
    public void testName() {
        Book book = new Book();
        book.setName("Abay Zholy");
        assertTrue(book.getName() == "Abay Zholy");
    }

    @Test
    public void testAuthor() {
        Book book = new Book();
        book.setAuthor("Mukaghali Makatayev");
        assertTrue(book.getAuthor() == "Mukaghali Makatayev");
    }

    @Test
    public void testCopy() {
        Book book = new Book();
        book.setCopies(500);
        assertTrue(book.getCopies() == 500);
    }




}
