package hatz.controller;

import hatz.domain.UserInfo;
import hatz.model.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author adi
 */
public class FilterController extends HttpServlet {

    public FilterController() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        DBManager dbManager = new DBManager();
        List<UserInfo> userInfos = dbManager.getAllUserInfo();

        RequestDispatcher rd;
        HttpSession session = request.getSession();

        String email = request.getParameter("emailF");
        String home_town = request.getParameter("home_townF");

        if (!email.equals("")) {
            userInfos = userInfos.stream()
                    .filter(userInfo -> userInfo.getEmail().equals(email))
                    .collect(Collectors.toList());
        } else {
            userInfos = userInfos.stream()
                    .filter(userInfo -> userInfo.getHome_town().equals(home_town))
                    .collect(Collectors.toList());
        }
        HttpSession session1 = request.getSession();
        session1.setAttribute("userInfo", userInfos);
        rd = request.getRequestDispatcher("/filter.jsp");
        rd.forward(request, response);
    }
}
