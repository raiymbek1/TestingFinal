package model;

public class ReaderBook<T> {
    private Reader reader;
    private T t;

    public ReaderBook(Reader reader, T t) {
        this.reader = reader;
        this.t = t;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "ReaderBook{" +
                "reader=" + reader +
                ", t=" + t +
                '}';
    }
}
