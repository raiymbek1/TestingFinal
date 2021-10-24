import model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookResourceTest {
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
