package hatz.model;

import hatz.domain.User;
import hatz.domain.UserInfo;

import java.sql.*;
import java.util.ArrayList;

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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab8web?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
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
            rs = stmt.executeQuery("select * from users where user='" + username + "' and password='" + password + "'");
            if (rs.next()) {
                u = new User(rs.getInt("id"), rs.getString("user"), rs.getString("password"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public UserInfo getUserInfo(int id) {
        ResultSet rs;
        UserInfo userInfo = null;
        try {
            rs = stmt.executeQuery("select * from userInfo where userId=" + id + ";");
            if (rs.next()) {
                userInfo = new UserInfo(rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("picture"),
                        rs.getInt("age"),
                        rs.getString("home_town"),
                        id);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public boolean createUser(String username, String password, String email, String fileName, Integer age, String home_town) {
        boolean r = false;
        String sql = "insert into users(user,password) values(\"" + username + "\",\"" + password + "\");";
        try {
            r = stmt.execute(sql);
            User user = this.authenticate(username, password);
            Integer bookId = user.getId();
            this.insertUserInfo(email, fileName, age, home_town, bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public void insertUserInfo(String email, String savePath, Integer age, String home_town, Integer bookId) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab8web?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            PreparedStatement pst = con.prepareStatement("insert into userInfo(email,picture,age,home_town,userId) values (?,?,?,?,?)");
            pst.setString(1, email);
            pst.setString(2, savePath);
            pst.setInt(3, age);
            pst.setString(4, home_town);
            pst.setInt(5, bookId);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserInfo> getAllUserInfo() {
        ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select * from userInfo");
            while (rs.next()) {
                userInfos.add(new UserInfo(rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("picture"),
                        rs.getInt("age"),
                        rs.getString("home_town"),
                        rs.getInt("userId")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfos;
    }

    public void updateUserInfo(String email, String savePath, Integer age, String home_town, Integer bookId) {
        try {
//            UserInfo userInfo = getUserInfo(bookId);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab8web?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "update userInfo set email=\"" + email +
                    "\", picture=\"" + savePath + "\", age=" +
                    String.valueOf(age) + ", home_town =\"" + home_town + "\" where userId=" + String.valueOf(bookId);
            System.out.println(sql);
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
