package servlets;

import controllers.ReaderBookController;
import model.Book;
import model.BorrowedBook;
import model.Reader;
import repositories.BorrowedBooksRepository;
import repositories.UserRepository;
import repositories.interfaces.IBookRepository;
import repositories.interfaces.IBorrowedBooksRepository;
import repositories.interfaces.IUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ServletAddDeleteReader")
public class ServletAddDeleteReader extends HttpServlet {
    IUserRepository userRepository = new UserRepository();
    ReaderBookController readerBookController = new ReaderBookController();
    IBorrowedBooksRepository brep = new BorrowedBooksRepository();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        Reader reader = new Reader(email, name, surname);
        if(name!=null && surname!=null && email!=null && !userRepository.checkEmail(email)) {
            userRepository.add(reader);
            out.println("New Reader is added!");
        }
        else{
            out.println("This email is already exists!");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<BorrowedBook> kek = brep.getBooksByUserId(id);
        if (!kek.isEmpty()) {
            printWriter.println("User has borrowed books");
        } else {
            printWriter.println("User has been deleted");
            readerBookController.deleteReaderFromBorrowedBooks(id);
            userRepository.deleteReaderById(id);
        }
    }
}
