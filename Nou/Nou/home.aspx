<%@ Page Language="C#" Inherits="Nou.home" %>
<!DOCTYPE html>
<html>
<head runat="server">
	<title>home</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
        <style>
            .ui-datepicker {
    width: 300px;
    height: 300px;
    margin: 5px auto 0;
    font: 12pt Arial, sans-serif;
    /*-webkit-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);
    -moz-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);*/
}

    .ui-datepicker table {
        width: 100%;
    }

.ui-datepicker-header {
    background: #3399ff;
    color: #ffffff;
    font-family:'Times New Roman';
    border-width: 1px 0 0 0;
    border-style: solid;
    border-color: #111;
}

.ui-datepicker-title {
    text-align: center;
    font-size: 15px;

}

.ui-datepicker-prev {
    float: left;
    cursor: pointer;
    background-position: center -30px;
}

.ui-datepicker-next {
    float: right;
    cursor: pointer;
    background-position: center 0px;
}

.ui-datepicker thead {
    background-color: #f7f7f7;

    /*border-bottom: 1px solid #bbb;*/
}

.ui-datepicker th {
    background-color:#808080;
    text-transform: uppercase;
    font-size: 8pt;
    color: #666666;
    /*text-shadow: 1px 0px 0px #fff;*/
    /*filter: dropshadow(color=#fff, offx=1, offy=0);*/
}

.ui-datepicker tbody td {
    padding: 0;
    /*border-right: 1px solid #808080;*/
}

    .ui-datepicker tbody td:last-child {
        border-right: 0px;
    }

.ui-datepicker tbody tr {
    border-bottom: 1px solid #bbb;
}

    .ui-datepicker tbody tr:last-child {
        border-bottom: 0px;
    }

.ui-datepicker a {
    text-decoration: none;
}

.ui-datepicker td span, .ui-datepicker td a {
    display: inline-block;
    /*font-weight: bold;*/
    text-align: center;
    width: 30px;
    height: 30px;
    line-height: 30px;
    color: #ffffff;
    /*text-shadow: 1px 1px 0px #fff;*/
    /*filter: dropshadow(color=#fff, offx=1, offy=1);*/
}

.ui-datepicker-calendar .ui-state-default {
      background: linear-gradient(#999999, #737373);
      color:#ffffff;
      height:40px;
      width:40px;

}

.ui-datepicker-calendar .ui-state-hover {
    background: #33adff;
    color: #FFFFFF;
}

.ui-datepicker-calendar .ui-state-active {
    background: #33adff;
    -webkit-box-shadow: inset 0px 0px 10px 0px rgba(0, 0, 0, .1);
    -moz-box-shadow: inset 0px 0px 10px 0px rgba(0, 0, 0, .1);
    box-shadow: inset 0px 0px 10px 0px rgba(0, 0, 0, .1);
    color: #e0e0e0;
    text-shadow: 0px 1px 0px #4d7a85;
    border: 1px solid #55838f;
    position: relative;
    margin: -1px;
}

.ui-datepicker-unselectable .ui-state-default {
    background: #D6E4BE;
    color: #000;
}
        </style>
</head>
<body>
	<form id="form1" runat="server">
            <asp:Button runat="server" Text="Logout" OnClick="LogoutClick"></asp:Button>
            <asp:Button runat="server" Text="Load Data" OnClick="LoadTabData"></asp:Button>

            <asp:PlaceHolder id="placeholder1" runat="server"></asp:PlaceHolder>
            <asp:Button runat="server" Text="Previous" OnClick="prev"></asp:Button>
            <asp:Button runat="server" Text="Next" OnClick="next"></asp:Button>
	</form>
    <form id="addReport" runat="server">
        <div>
            <table>
                    <tr>
                        <td>Enter type</td>
                        <td><asp:TextBox id="type" runat="server"></asp:TextBox></td>
                    </tr>
                    <tr>
                        <td>Enter severity</td>
                        <td>
                            <asp:DropDownList id="severity" name="severity" runat="server">
                                <asp:ListItem value="High">High</asp:ListItem>
                                <asp:ListItem value="Moderate">Moderate</asp:ListItem>
                                <asp:ListItem value="Low">Low</asp:ListItem>
                            </asp:DropDownList>
                        </td>
                    </tr>
                    <tr>
                        <td>Enter date</td>
                        <td><asp:TextBox autocomplete="off" TextMode="Date" id="date" runat="server"></asp:TextBox></td>
                    </tr>
                    <tr>
                        <td>Enter log</td>
                        <td><asp:TextBox id="log" runat="server"></asp:TextBox></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <asp:Button runat="server" Text="Add Report" OnClick="addReportFunc"></asp:Button>
                        </td>
                    </tr>
                </table>        
        </div>
    </form>
        <form id="deleteReport" runat="server">
            <div>
            <table>
                    <tr>
                        <td>Enter the id of the delete report</td>
                        <td><asp:TextBox id="reportID" runat="server"></asp:TextBox></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <asp:Button runat="server" Text="Delete Report" OnClick="deleteReportFunc"></asp:Button>
                        </td>
                    </tr>
                </table>        
        </div>
        </form>
        <form id="filterForm" runat="server">
            <div>
            <table>
                    <tr>
                        <td>Enter severity</td>
                        <td>
                            <asp:DropDownList id="severityFilter" name="severityFilter" runat="server">
                                <asp:ListItem value="High">High</asp:ListItem>
                                <asp:ListItem value="Moderate">Moderate</asp:ListItem>
                                <asp:ListItem value="Low">Low</asp:ListItem>
                            </asp:DropDownList>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <asp:Button runat="server" Text="Filter" OnClick="filterFunc"></asp:Button>
                        </td>
                    </tr>
                </table>
                <asp:PlaceHolder id="placeholder2" runat="server"></asp:PlaceHolder>
        </div>
        </form>
        <script>  
            $(document).ready(function () {
             $("#date").datepicker();
            });
            </script>
</body>
</html>
