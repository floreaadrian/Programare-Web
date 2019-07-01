<%@ Page Language="C#" Inherits="AspPlanete.home" %>
<!DOCTYPE html>
<html>
<head runat="server">
	<title>home</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
</head>
<body>
	<form id="form1" runat="server">
	</form>
        <script>
            $.ajax({
                mehod: "GET",
                url: "http://127.0.0.1:8080/api/empty/names"
            }).done(function(data){
                console.log(data);
            });
            </script>
</body>
</html>
