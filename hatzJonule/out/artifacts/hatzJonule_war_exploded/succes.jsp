<%@ page import="hatz.domain.User" %>
<%@ page import="hatz.domain.UserInfo" %>
<%@ page import="hatz.model.DBManager" %>
<%@ page
        contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Insert title here</title>
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/ajax-utils.js"></script>
    <script src="js/main.js"></script>
    <style>
        table {
            border: 1px solid #666;
            width: 100%;
        }

        th {
            background: #f8f8f8;
            font-weight: bold;
            padding: 2px;
        }

        td {
            max-width: 250px;
        }
    </style>
</head>
<body>
<%! User user; %> <% user = (User) session.getAttribute("user");
    if
    (user != null) {
        out.println("Welcome " + user.getUsername());
    } else {
        RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
        rd.forward(request, response);
    }%>
<br/>
<form action="UploadInfo" method="post" enctype='multipart/form-data'>
    <label>
        Email: <input type="text" name="email"/>
    </label>
    <br>
    <label>
        File: <input type="file" name="file" multiple/>
    </label>
    <br>
    <label>
        Age: <input type="text" name="age" multiple/>
    </label>
    <br>
    <label>
        Home town: <input type="text" name="home_town" multiple/>
    </label>
    <br/>
    <input type="submit" value="Update"/>
</form>
<a href="LogoutController">Logout</a>
<table id="UserInfo">
    <tr>
        <th>Image</th>
        <th>Email</th>
        <th>Age</th>
        <th>Home town</th>
    </tr>
    <%
        DBManager dbManager = new DBManager();
        UserInfo userInfo = dbManager.getUserInfo(user.getId());

        out.println("<tr> <td><img width=\"200px\"src=\"img/" + userInfo.getPicture() + "\"/></td>" +
                "<td>" + userInfo.getEmail() + "</td>" +
                "<td>" + userInfo.getAge() + "</td>" +
                "<td>" + userInfo.getHome_town() + "</td> </tr>");

    %>
</table>
<br>
<form action="FilterController" method="post">
    <label>
        Email: <input type="text" name="emailF"/>
    </label>
    <br>
    <label>
        Home town: <input type="text" name="home_townF"/>
    </label>
    <br/>
    <input type="submit" value="Filter"/>
</form>
</body>

</html>
