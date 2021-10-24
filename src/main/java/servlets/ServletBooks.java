package servlets;

import model.Book;
import model.Reader;
import org.glassfish.jersey.client.ClientConfig;
import repositories.BookRepository;
import repositories.UserRepository;
import repositories.interfaces.IBookRepository;
import repositories.interfaces.IUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


@WebServlet(name = "ServletBooks")
public class ServletBooks extends HttpServlet {
    IBookRepository bookRepository = new BookRepository();

    private WebTarget getWebTarget() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        return client.target("http://localhost:8080/CalendarEvents_war_exploded/rest/books");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebTarget target = getWebTarget();
        String bookId = request.getParameter("book_id");
        Book book = target.path(bookId).request().accept(MediaType.APPLICATION_JSON).get(Book.class);
        request.setAttribute("book",book);


        request.getRequestDispatcher("jsp/redactBookPage.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Stack<Book> books = bookRepository.query();
        request.setAttribute("readers",books);
        request.getRequestDispatcher("jsp/usersPage.jsp").forward(request,response);
    }
}
