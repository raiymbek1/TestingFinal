package repositories.interfaces;

import model.Book;
import model.BorrowedBook;
import model.ReaderBook;

import java.util.ArrayList;

public interface IBorrowedBooksRepository {
    void addToBookList(int reader_id, int book_id);
    void removeFromBookList(int borrow_id);
    ArrayList<BorrowedBook> getBooksByUserId(int id_borrowedBook);
    void removeReaderBookList(int reader_id);
    boolean addBookCheck(Book book);
    void removeBookBookList(int book_id);
    ArrayList<ReaderBook<Book>> query();
}
