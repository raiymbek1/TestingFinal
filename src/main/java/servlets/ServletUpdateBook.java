package servlets;

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

@WebServlet(name = "ServletUpdateBook")
public class ServletUpdateBook extends HttpServlet {
    IBookRepository bookRepository = new BookRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String id = request.getParameter("book_id");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        int copies = Integer.parseInt(request.getParameter("copies"));
        Book book = new Book(Integer.parseInt(id),name,author,copies);
        if(name!=null && author!=null && copies!=0) {
            bookRepository.updateBook(book);
            out.println("Changed!");
        }
        else{
            out.println("Please fill all the blanks!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
