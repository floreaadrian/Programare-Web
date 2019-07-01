package space.controller;
import space.model.DBManager;
import space.domain.Astronaut;

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
                          HttpServletResponse response) throws ServletException, IOException
    {

        RequestDispatcher rd;
        HttpSession session = request.getSession();
        Astronaut astroName = (Astronaut) session.getAttribute("name");
        if (astroName != null) {
            System.out.println("hi");
            rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            return;
        }
        String name = request.getParameter("name");

        DBManager dbmanager = new DBManager();
        Astronaut astro = dbmanager.authenticate(name);
        if (astro != null) {
            rd = request.getRequestDispatcher("/success.jsp");
            HttpSession session1 = request.getSession();
            session1.setAttribute("name", astro);
        } else {
            rd = request.getRequestDispatcher("/error.jsp");
        }
        rd.forward(request, response);
    }
}