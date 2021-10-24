package model;

import controllers.ReaderBookController;
import repositories.BookRepository;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ReaderBookController readerBookController = new ReaderBookController();
        System.out.println(readerBookController.getBooksBorrowed());
    }
}
