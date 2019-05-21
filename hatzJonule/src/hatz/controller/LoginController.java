package hatz.controller;

/**
 * @author adi
 */


import hatz.domain.User;
import hatz.model.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd;
        HttpSession session = request.getSession();
        User userName = (User) session.getAttribute("user");
        if (userName != null) {
            System.out.println("hi");
            rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DBManager dbmanager = new DBManager();
        User user = dbmanager.authenticate(username, password);
        if (user != null) {
            rd = request.getRequestDispatcher("/succes.jsp");
            HttpSession session1 = request.getSession();
            session1.setAttribute("user", user);
        } else {
            rd = request.getRequestDispatcher("/error.jsp");
        }
        rd.forward(request, response);
    }

}