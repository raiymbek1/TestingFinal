package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ServletLogin")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username.equals("admin") && password.equals("admin")){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60*60*10);
            response.addCookie(cookie);
            response.sendRedirect("jsp/index.jsp");
        }else if(username.equals("raiym") && password.equals("raiym")){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60*60*10);
            response.addCookie(cookie);
            response.sendRedirect("jsp/reader.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            session.removeAttribute("username");
            Cookie[] cookies = request.getCookies();
            String cookieName = "username";
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    c.setMaxAge(0);
                    response.addCookie(c);
                    break;
                }
            }
            session.invalidate();
            request.getRequestDispatcher("jsp/login.jsp").forward(request,response);
    }
}
