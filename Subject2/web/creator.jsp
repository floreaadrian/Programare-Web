<%@ page import="Domain.User" %><%--
  Created by IntelliJ IDEA.
  Domain.User: adi
  Date: 2019-06-24
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Creator</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

</head>
<body>
<%! User user; %> <% user = (User) session.getAttribute("user");
    if
    (user != null && user.getRole() == 1) {
        out.println("Welcome " + user.getName());
        out.println("<br>");
    } else {
        RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
        rd.forward(request, response);
    }%>
<div>
    <form id="addDataForm">
        <label>Title:
            <input type="text" name="title">
        </label>
        <label>Description:
            <input type="text" name="desc">
        </label>
        <label>Url:
            <input type="text" name="url">
        </label>
        <label>
            <input type="submit" value="Add" name="Add">
        </label>
    </form>
</div>
<button id="addAllBtn">Add all</button>
<button id="deleteAll">Delete all</button>
<script>
    var dataToPush = [];
    $("#addDataForm").on("submit", function (e) {
        e.preventDefault();
        var that = $(this),
            url = that.attr("action"),
            type = that.attr("method"),
            data = {};
        that.find("[name]").each(function (index, value) {
            var that = $(this),
                name = that.attr("name"),
                value = that.val();
            data[name] = value;
        });
        dataToPush.push(data);
    });
    $("#addAllBtn").click(function () {
        dataToPush = {"data": dataToPush};
        console.log(dataToPush);
        $.ajax({
            url: "AddData",
            method: "POST",
            dataType: 'JSON',
            data: JSON.stringify(dataToPush)
        }).done(function (data) {
            console.log("ok");
        })
    });
    $("#deleteAll").click(function () {
        $.ajax({
            url: "DeleteAll",
            method: "POST"
        }).done(function (data) {
            console.log(data);
        })
    });
</script>
</body>
</html>
