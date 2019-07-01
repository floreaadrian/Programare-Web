package space.model;

import space.domain.Astronaut;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private Statement stmt;
    private PreparedStatement preparedStatement;

    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/subject3?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("eroare la connect:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Astronaut authenticate(String username) {
        ResultSet rs;
        Astronaut a = null;
        System.out.println(username);
        try {
            rs = stmt.executeQuery("select * from astronauts where name='" + username + "'");
            if (rs.next()) {
                a = new Astronaut(rs.getInt("id"), rs.getInt("civilizationID"), rs.getString("name"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public ArrayList<String> getColours() {
        ArrayList<String> colours = new ArrayList<String>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select color from planets");
            while (rs.next()) {
                colours.add(rs.getString("color"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colours;
    }

}
