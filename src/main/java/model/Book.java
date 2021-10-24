package model;

public class Book extends BookAbstract{
    public Book(int id, String ISBN, String name, String author, int copies) {
        super.id = id;
        super.ISBN = ISBN;
        super.name = name;
        super.author = author;
        super.copies = copies;
    }

    public Book(){}

    public Book(int id, String name, String author, int copies) {
        super.id = id;
        super.ISBN = ISBN;
        super.name = name;
        super.author = author;
        super.copies = copies;
    }

    public Book(String name, String author, int copies) {
        super.ISBN = ISBN;
        super.name = name;
        super.author = author;
        super.copies = copies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", copies=" + copies +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        String expression = ((Book)o).getName();
        return this.name.compareTo(expression);
    }
}
