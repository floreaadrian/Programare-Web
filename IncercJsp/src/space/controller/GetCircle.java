package space.controller;

import org.json.simple.JSONArray;
import space.model.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetCircle extends HttpServlet {

    public GetCircle() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        DBManager dbmanager = new DBManager();
        List<String> color = dbmanager.getColours();
        JSONArray list = new JSONArray();
        list.addAll(color);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(list);
        out.flush();
    }
}