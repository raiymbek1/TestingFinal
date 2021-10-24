package servlets;

import model.Reader;
import repositories.PostgresRepository;
import repositories.UserRepository;
import repositories.interfaces.IUserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ServletReaders")
public class ServletReaders extends HttpServlet {
    IUserRepository userRepository = new UserRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("reader_id"));
        Reader reader = userRepository.findReaderById(id);
        System.out.println(reader);
        request.setAttribute("reader",reader);
        request.getRequestDispatcher("jsp/redactUserData.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Reader> readers = userRepository.query();
        request.setAttribute("readers",readers);
        request.getRequestDispatcher("jsp/usersPage.jsp").forward(request,response);
    }
}
