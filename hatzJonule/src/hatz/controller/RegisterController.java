package hatz.controller;

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
public class RegisterController extends HttpServlet {
    public RegisterController() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginController loginController = new LoginController();
        String user = (String) session.getAttribute("user");
        System.out.println("hiii" + user);
        if (!(user == null || user.equals(""))) {
            loginController.doPost(request, response);
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
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
        ///data init
        String home_town = request.getParameter("home_town");
        String email = request.getParameter("email");
        Integer age = Integer.parseInt(request.getParameter("age"));
        RequestDispatcher rd = null;
        DBManager dbmanager = new DBManager();
        boolean exist = dbmanager.createUser(username, password, email, fileName, age, home_town);
        loginController.doPost(request, response);
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
