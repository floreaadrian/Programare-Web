import Domain.Content;
import Domain.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * @author adi
 */
@WebServlet("/AddData")
public class AddData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String jsonStr = "";
        HttpSession session = request.getSession();
        DBManager dbmanager = new DBManager();
        User user = (User) session.getAttribute("user");
        if (br != null) {
            jsonStr = br.readLine();
        }
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(jsonStr);
            JSONArray jsonArray = (JSONArray) json.get("data");
            jsonArray.forEach(e -> {
                try {
                    JSONObject newJson = (JSONObject) parser.parse(e.toString());
                    System.out.println(newJson);
                    Content content = new Content(new Date().toString(),
                            newJson.get("title").toString(),
                            newJson.get("desc").toString(),
                            newJson.get("url").toString(),
                            user.getUserId());
                    dbmanager.addContent(content);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

            });

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
