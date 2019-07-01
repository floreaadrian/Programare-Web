<%@ Page Language="C#" Inherits="Subject4.Default" %>
<!DOCTYPE html>
<html>
<head runat="server">
	<title>Default</title>
</head>
<body>
	<form id="form1" runat="server">
        <div>
            <table>
                    <tr>
                        <td>Enter username</td>
                        <td><asp:TextBox id="t1" runat="server"></asp:TextBox></td>
                    </tr>
                    <tr>
                        <td>Enter password</td>
                        <td><asp:TextBox id="t2" runat="server" TextMode="Password"></asp:TextBox></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <asp:Button runat="server" Text="Login" OnClick="Button_Click"></asp:Button>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <asp:Label id="Label1" runat="server" Text=""></asp:Label>
                        </td>
                    </tr>
                </table>        
        </div>
    </form>
</body>
</html>
