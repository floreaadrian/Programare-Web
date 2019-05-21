<%@ page import="hatz.domain.User" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        form {
            margin-left: auto;
            margin-right: auto;
            width: 400px;
        }
    </style>
</head>
<body>
<%! User user; %>
<% user = (User) session.getAttribute("user");
    if (user != null) {
        RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
        rd.forward(request, response);
    }
%>
<form action="RegisterController" method="post" enctype='multipart/form-data'>
    Enter username : <input type="text" name="username"> <BR>
    Enter password : <input type="password" name="password"> <BR>
    <label>
        Email: <input type="text" name="email" multiple/>
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
    <input type="submit" value="Register"/>
</form>
</body>
</html>