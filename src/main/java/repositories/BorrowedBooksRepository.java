package repositories;
import model.Book;
import model.BorrowedBook;
import model.Reader;
import model.ReaderBook;
import repositories.interfaces.IBookRepository;
import repositories.interfaces.IBorrowedBooksRepository;
import repositories.interfaces.IDBRepository;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;

public class BorrowedBooksRepository implements IBorrowedBooksRepository {
    private IDBRepository dbrepo = new PostgresRepository();
    private boolean next;

    public void addToBookList(int reader_id, int book_id) {
        try {
            String sql = "INSERT INTO borrowedbooks(reader_id,book_id)" +
                    "VALUES(?, ?)";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setInt(1,reader_id);
            stmt.setInt(2,book_id);
            stmt.execute();
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeFromBookList(int borrow_id) {
        try {
            String sql = "DELETE FROM borrowedbooks WHERE id = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setInt(1, borrow_id);
            stmt.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeReaderBookList(int reader_id) {
        try {
            String sql = "DELETE FROM borrowedbooks WHERE reader_id = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setInt(1, reader_id);
            stmt.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeBookBookList(int book_id) {
        try {
            String sql = "DELETE FROM borrowedbooks WHERE book_id = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setInt(1, book_id);
            stmt.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean addBookCheck(Book book){
        try{
            String sql = "select count(*) from borrowedbooks where book_id = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setInt(1, book.getId());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                if(rs.getInt("count")==book.getCopies()){
                    return false;
                }
                else{
                    return true;
                }
            }
            return false;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public ArrayList<ReaderBook<Book>> query() {
        try {
            IUserRepository userRepository = new UserRepository();
            IBookRepository bookRepository = new BookRepository();
            String sql = "Select * from borrowedbooks";
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<ReaderBook<Book>> readerBooks = new ArrayList<>();
            while (rs.next()) {
                Reader reader = userRepository.findReaderById(rs.getInt("reader_id"));
                Book book = bookRepository.findBookById(rs.getInt("book_id"));
                ReaderBook<Book> readerBook = new ReaderBook<>(reader,book);
                readerBooks.add(readerBook);
            }
            return readerBooks;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public ArrayList<BorrowedBook> getBooksByUserId(int user_id)  {
        try {
            String sql = "select b.id as bb_id,b1.id,b1.isbn,b1.name,b1.author,b1.copies from books b1 inner join borrowedbooks b on b1.id = b.book_id where b.reader_id = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setInt(1, user_id);
            ResultSet rs = stmt.executeQuery();
            ArrayList<BorrowedBook> books = new ArrayList<>();
            while (rs.next()) {
                BorrowedBook book = new BorrowedBook(
                        rs.getInt("bb_id"),
                        rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getInt("copies")
                );
            books.add(book);
          }
          return books;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

}
