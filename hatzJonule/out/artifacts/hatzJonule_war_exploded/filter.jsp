<%@ page import="hatz.domain.UserInfo" %>
<%@ page import="java.util.ArrayList" %>
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
<table id="UserInfo">
    <tr>
        <th>Image</th>
        <th>Email</th>
        <th>Age</th>
        <th>Home town</th>
    </tr>
    <%
        ArrayList<UserInfo> userInfos = (ArrayList<UserInfo>) session.getAttribute("userInfo");
        for (UserInfo user : userInfos) {
            out.println("<tr> <td><img width=\"200px\"src=\"img/" + user.getPicture() + "\"/></td>" +
                    "<td>" + user.getEmail() + "</td>" +
                    "<td>" + user.getAge() + "</td>" +
                    "<td>" + user.getHome_town() + "</td>" +
                    "<td>" + user.getUserId() + "</td> </tr>");
        }
    %>
</table>
</body>

</html>