package servlets;

import controllers.ReaderBookController;
import model.Book;
import model.Reader;
import repositories.BookRepository;
import repositories.interfaces.IBookRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletAddDeleteBook")
public class ServletAddDeleteBook extends HttpServlet {
    IBookRepository bookRepository = new BookRepository();
    ReaderBookController readerBookController = new ReaderBookController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        int copies = Integer.parseInt(request.getParameter("copies"));
        Book book = new Book(name,author,copies);
        if(name!=null && author!=null && copies!=0) {
            bookRepository.add(book);
            out.println("New book is added!");
        }
        else{
            out.println("failed to add!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        readerBookController.removeBookFromBookList(id);
        bookRepository.deleteBookById(id);
    }
}
