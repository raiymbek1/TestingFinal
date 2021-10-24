package repositories;

import repositories.interfaces.IDBRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresRepository implements IDBRepository {
    @Override
        public Connection getConnection() {
            try {
                Class.forName("org.postgresql.Driver");
                String connStr = "jdbc:postgresql://localhost:5432/books";
                Connection conn = DriverManager.getConnection(connStr, "postgres", "123");
                return conn;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
    }
}
