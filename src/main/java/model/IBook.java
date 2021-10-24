package model;

public interface IBook extends Comparable {
    public int getId();

    public void setId(int id);

    public String getISBN();

    public void setISBN(String ISBN);

    public String getName();

    public void setName(String name);

    public String getAuthor();

    public void setAuthor(String author);

    public int getCopies();

    public void setCopies(int copies);
}
