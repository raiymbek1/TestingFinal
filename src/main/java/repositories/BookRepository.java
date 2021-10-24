package repositories;

import model.Book;
import repositories.interfaces.IBookRepository;
import repositories.interfaces.IDBRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

public class BookRepository implements IBookRepository {
    private IDBRepository dbrepo = new PostgresRepository();

    public void add(Book entity) {
        try {
            String sql = "INSERT INTO books(isbn, name, author, copies)" +
                    "VALUES(random_string(6), ?, ?, ?)";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getAuthor());
            stmt.setInt(3, entity.getCopies());
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Stack<Book> query() {
        try {
            String sql = "Select * from books";
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Stack<Book> books = new Stack<>();
            while (rs.next()) {
                Book book= new Book(
                        rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getInt("copies")
                );
                books.add(book);
            }
            return books;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Book findBookById(int id) {
        try {
            String sql = "SELECT * FROM books WHERE id = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getInt("copies")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteBookById(int id) {
        try {
            String sql = "DELETE FROM books WHERE id = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        try {
            String sql = "UPDATE books SET name = ?, author = ?, copies = ? WHERE id = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, book.getName());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getCopies());
            stmt.setInt(4, book.getId());
            stmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Book findBookByISBN(String data) {
        try {
            String sql = "SELECT * FROM books WHERE isbn = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, data);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getInt("copies"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean checkISBN(int isbn) {
        try {
            String sql = "SELECT * FROM books WHERE isbn = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setInt(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean checkISBNExcept(Book book) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, book.getISBN());
            ResultSet rs = stmt.executeQuery();
            Book bookPrevious = findBookById(book.getId());
            if (rs.next()) {
                if(bookPrevious.getISBN() == book.getISBN()){
                    return false;
                }
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }
}
