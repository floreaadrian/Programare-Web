package hatz.controller;

import hatz.domain.User;
import hatz.model.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author adi
 */

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UploadInfo extends HttpServlet {
    public UploadInfo() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset-UMF-8");
        PrintWriter out = response.getWriter();
        Part part = request.getPart("file");
        String fileName = extractFileName(part);
        out.println(fileName);
        File f = new File(fileName);
        fileName = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("/") + 1);
        String savePath = "/Users/adrianflorea/Codes/ProgramareWeb/hatzJonule/web/img" + File.separator + fileName;
        out.println(savePath);
        File fileSaveDir = new File(savePath);
        part.write(savePath + File.separator);
        /////
        HttpSession session = request.getSession();

        ///data init
        String home_town = request.getParameter("home_town");
        String email = request.getParameter("email");
        Integer age = Integer.parseInt(request.getParameter("age"));
        User user = (User) session.getAttribute("user");
        Integer bookId = user.getId();
        DBManager dbmanager = new DBManager();
        dbmanager.updateUserInfo(email, fileName, age, home_town, bookId);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/succes.jsp");
        rd.forward(request, response);

//        try {
//
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab8web?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
//            PreparedStatement pst = con.prepareStatement("insert into userInfo(email,picture,age,home_town,userId) values (?,?,?,?,?)");
//            pst.setString(1, email);
//            pst.setString(2, savePath);
//            pst.setInt(3, age);
//            pst.setString(4, home_town);
//            pst.setInt(5, bookId);
//            pst.executeUpdate();
//            out.println("Added");
//
//        } catch (Exception e) {
//            out.println(e);
//        }

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }


        return "";


    }
}
