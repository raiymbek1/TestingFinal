package model;

public interface IUser extends Comparable{
    int getId();
    void setId(int id);
    String getEmail();
    void setEmail(String email);
    String getName();
    void setName(String name);
    String getSurname();
    void setSurname(String surname);
}
