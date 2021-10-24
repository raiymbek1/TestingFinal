package servlets;

import controllers.ReaderBookController;
import model.Book;
import model.Reader;
import repositories.BookRepository;
import repositories.UserRepository;
import repositories.interfaces.IBookRepository;
import repositories.interfaces.IUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ServletBookListController")
public class ServletBookListController extends HttpServlet {
    ReaderBookController readerBookController = new ReaderBookController();
    IUserRepository userRepository = new UserRepository();
    IBookRepository bookRepository = new BookRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("ISBN");
        int reader_id = Integer.parseInt(request.getParameter("reader_id"));
        Book book = bookRepository.findBookByISBN(isbn);
        PrintWriter out = response.getWriter();
        if(readerBookController.addBookChecker(book)){
            readerBookController.addToBookList(reader_id,book.getId());
            out.println("You borrow a book");
        }
        else{
            out.println("Sorry, all books are borrowed");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int borrow_id = Integer.parseInt(request.getParameter("borrow_id"));
        int reader_id = Integer.parseInt(request.getParameter("reader_id"));
        readerBookController.deleteBookFromBookList(borrow_id);
        Reader reader = userRepository.findReaderById(reader_id);
        request.setAttribute("reader",reader);
        request.getRequestDispatcher("jsp/redactUserData.jsp").forward(request,response);
    }
}
