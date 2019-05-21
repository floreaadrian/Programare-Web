package hatz.controller;

import hatz.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author adi
 */
public class LogoutController extends HttpServlet {

    public LogoutController() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd;
        HttpSession session = request.getSession();
        User userName = (User) session.getAttribute("user");
        if (userName == null) {
            rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        } else {
            session.invalidate();
            response.sendRedirect("http://localhost:8080/");
        }
    }

}