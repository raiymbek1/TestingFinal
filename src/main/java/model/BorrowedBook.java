package model;

public class BorrowedBook extends BookAbstract{
    private int id_borrowedBook;

    public BorrowedBook(int id_borrwedBook,int id, String ISBN, String name, String author, int copies) {
        this.id_borrowedBook = id_borrwedBook;
        super.id = id;
        super.ISBN = ISBN;
        super.name = name;
        super.author = author;
        super.copies = copies;
    }

    public BorrowedBook(){
    }

    public int getId_borrowedBook(){
        return this.id_borrowedBook;
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "id_borrowedBook=" + id_borrowedBook +
                ", id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", copies=" + copies +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        String expression = ((BorrowedBook)o).getName();
        return this.name.compareTo(expression);
    }
}
