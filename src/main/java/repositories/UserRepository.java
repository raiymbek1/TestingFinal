package repositories;

import model.Reader;
import repositories.interfaces.IDBRepository;
import repositories.interfaces.IUserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Stack;

public class UserRepository implements IUserRepository {
    private IDBRepository dbrepo = new PostgresRepository();

    public void add(Reader entity) {
        try {
            String sql = "INSERT INTO users(email, name, surname) " +
                    "VALUES(?, ?, ?)";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, entity.getEmail());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getSurname());
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Reader> query() {
        try {
            String sql = "Select * from users";
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Reader> users = new ArrayList<>();
            while (rs.next()) {
                Reader user = new Reader(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("surname")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Reader findReaderById(int id) {
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reader(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("surname")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteReaderById(int id) {
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateReader(Reader reader) {
        try {
            String sql = "UPDATE users SET name = ?, surname = ?, email = ? WHERE id= ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, reader.getName());
            stmt.setString(2, reader.getSurname());
            stmt.setString(3, reader.getEmail());
            stmt.setInt(4, reader.getId());
            stmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Stack<Reader> findReaderByName(String data) {
        try {
            String sql = "SELECT * FROM users WHERE name = ?,surname = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, data);
            ResultSet rs = stmt.executeQuery();
            Stack<Reader> users = new Stack<>();
            while (rs.next()) {
                Reader user = new Reader(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("surname")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean checkEmail(String email) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, email);
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

    public boolean checkEmailExcept(Reader reader) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, reader.getEmail());
            ResultSet rs = stmt.executeQuery();
            Reader readerPrevious = findReaderById(reader.getId());
            if (rs.next()) {
                if(readerPrevious.getEmail().equals(reader.getEmail())){
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
