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
    <title>Reader</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
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
    (user != null && user.getRole() == 2) {
        out.println("Welcome " + user.getName());
        out.println("<br>");
    } else {
        RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
        rd.forward(request, response);
    }%>
<table id="tabelu">
    <tr>
        <th>Date</th>
        <th>Desc</th>
        <th>Title</th>
        <th>Url</th>
        <th>UserId</th>
    </tr>
</table>
<script>
    var index = 0;
    var content = [];
    var upperLimit = 4;
    var arraysMatch = function (arr1, arr2) {

        // Check if the arrays are the same length
        if (arr1.length !== arr2.length) return false;

        // Check if all items exist and are in the same order
        for (var i = 0; i < arr1.length; i++) {
            if (arr1[i] !== arr2[i]) return false;
        }

        // Otherwise, return true
        return true;
    };


    function getData() {
        $.ajax({
            method: "GET",
            url: "GetContent",
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        }).done(function (data) {
            upperLimit = Math.min(upperLimit, data.length);
            if (content.length === 0) {
                content = data;
            } else if (arraysMatch(data, content)) {
                alert("new data");
                content = data;
                index = 0;
            }
            if (index === upperLimit)
                index = 0;
            drawTable(content[index]);
            index++;
            console.log(index);
        });
    }

    showAndGetData();

    function showAndGetData() {
        getData();
    }

    setInterval(showAndGetData, 5000);

    function cleanTable() {
        $("#tabelu")
            .find("tr:gt(0)")
            .remove();
    }

    function drawTable(data) {
        cleanTable();
        drawRow(data);
    }

    function drawRow(rowData) {
        var row = $("<tr />");
        console.log(rowData.date);
        $("#tabelu").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
        row.append($("<td>" + rowData.date + "</td>"));
        row.append($("<td>" + rowData.desc + "</td>"));
        row.append($("<td>" + rowData.title + "</td>"));
        row.append($("<td>" + rowData.url + "</td>"));
        row.append($("<td>" + rowData.userid + "</td>"));
    }

</script>
</body>
</html>
