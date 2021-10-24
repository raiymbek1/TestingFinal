package controllers;

import model.*;
import repositories.BorrowedBooksRepository;
import repositories.interfaces.IBorrowedBooksRepository;

import java.util.ArrayList;

public class ReaderBookController {
    IBorrowedBooksRepository borrowedBooksRepository = new BorrowedBooksRepository();

    public ArrayList<BorrowedBook> getBookByReaderId(int id){
        ArrayList<BorrowedBook> books = borrowedBooksRepository.getBooksByUserId(id);
        return books;
    }

    public ArrayList<ReaderBook<Book>> getBooksBorrowed(){
        return borrowedBooksRepository.query();
    }

    public void deleteBookFromBookList(int borrow_id){
        borrowedBooksRepository.removeFromBookList(borrow_id);
    }

    public void deleteReaderFromBorrowedBooks(int reader_id){
        borrowedBooksRepository.removeReaderBookList(reader_id);
    }

    public void removeBookFromBookList(int book_id){
        borrowedBooksRepository.removeBookBookList(book_id);
    }

    public boolean addBookChecker(Book book){
        return borrowedBooksRepository.addBookCheck(book);
    }

    public void addToBookList(int reader_id,int book_id){
        borrowedBooksRepository.addToBookList(reader_id,book_id);
    }
}
