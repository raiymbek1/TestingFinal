package repositories.interfaces;

import model.Book;

import java.util.Stack;

public interface IBookRepository {
    void add(Book entity);
    Stack<Book> query();
    Book findBookById(int id);
    void deleteBookById(int id);
    void updateBook(Book book);
    Book findBookByISBN(String data);
    boolean checkISBN(int isbn);
    boolean checkISBNExcept(Book book);
}
