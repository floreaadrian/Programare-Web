package hatz.controller;

import hatz.domain.UserInfo;
import hatz.model.DBManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author adi
 */
public class UserInfoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userid = Integer.parseInt(request.getParameter("userid"));
        System.out.println(userid);
        response.setContentType("application/json");
        DBManager dbmanager = new DBManager();
        UserInfo assets = dbmanager.getUserInfo(userid);
        System.out.println(assets);
        JSONObject jObj = new JSONObject();
        jObj.put("id", assets.getId());
        jObj.put("email", assets.getEmail());
        jObj.put("picture", assets.getPicture());
        jObj.put("age", assets.getAge());
        jObj.put("home_town", assets.getHome_town());
        jObj.put("userId",assets.getUserId());

        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jObj.toJSONString());
        out.flush();
    }
}
