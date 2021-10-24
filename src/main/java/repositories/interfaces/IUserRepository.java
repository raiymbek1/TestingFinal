package repositories.interfaces;

import model.Reader;

import java.util.ArrayList;
import java.util.Stack;

public interface IUserRepository {
    void add(Reader entity);
    ArrayList<Reader> query();
    Reader findReaderById(int id);
    Stack<Reader> findReaderByName(String data);
    boolean checkEmail(String email);
    void deleteReaderById(int id);
    void updateReader(Reader reader);
    boolean checkEmailExcept(Reader reader);
}
