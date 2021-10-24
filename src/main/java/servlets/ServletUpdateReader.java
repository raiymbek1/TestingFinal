package servlets;

import model.Reader;
import repositories.UserRepository;
import repositories.interfaces.IUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletUpdateReader")
public class ServletUpdateReader extends HttpServlet {
    IUserRepository userRepository = new UserRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        Reader reader = new Reader(id, email, name, surname);
        if(name!=null && surname!=null && email!=null && !userRepository.checkEmailExcept(reader)) {
            userRepository.updateReader(reader);
            out.println("Changed!");
        }
        else{
            out.println("This email is already exists!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
