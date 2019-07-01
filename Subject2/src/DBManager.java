import Domain.Content;
import Domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author adi
 */
public class DBManager {
    private Statement stmt;
    private PreparedStatement preparedStatement;

    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/subject2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("eroare la connect:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public User authenticate(String username, String password) {
        ResultSet rs;
        User u = null;
        System.out.println(username + " " + password);
        try {
            rs = stmt.executeQuery("select * from users where name='" + username + "' and password='" + password + "'");
            if (rs.next()) {
                u = new User(rs.getInt("id"), rs.getString("name"), rs.getInt("role"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean addContent(Content content) {
        boolean r = false;
        String sql = "insert into content(date,title,description,url,userid)" +
                " values(\"" + content.getDate() + "\"," +
                "\"" + content.getTitle() + "\"," +
                "\"" + content.getDesc() + "\"," +
                "\"" + content.getUrl() + "\"," + content.getUserid() + ");";
        System.out.println(sql);
        try {
            r = stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public boolean deleteAll(int userId) {
        boolean r = false;
        String sql = "delete from content where userid=" + userId;
        try {
            r = stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public List<Content> getContent() {
        ArrayList<Content> contents = new ArrayList<>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select * from content ORDER BY id LIMIT 0,4");
            while (rs.next()) {
                contents.add(new Content(
                        rs.getString("date"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("url"),
                        rs.getInt("userid")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contents;
    }
}
