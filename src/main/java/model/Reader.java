package model;

public class Reader extends User{

    public Reader(int id,String email, String name, String surname) {
        super.id = id;
        super.email = email;
        super.name = name;
        super.surname = surname;
    }

    public Reader(String email, String name, String surname) {
        super.id = id;
        super.email = email;
        super.name = name;
        super.surname = surname;
    }

    public Reader() {
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        int id = ((Reader)o).getId();
        return this.getId()-id;
    }
}
