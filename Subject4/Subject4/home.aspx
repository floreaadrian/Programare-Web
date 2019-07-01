<%@ Page Language="C#" Inherits="Subject4.home" %>
<!DOCTYPE html>
<html>
    <head runat="server">
        <title>home</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    </head>
    <body>
        <div>
            IDObject:
            <input id="idobjectinput" type="text" />
            <button id="getobjbtn1">Get obj!</button>
            <button id="getVers1">Get vers!</button>
        </div>
        <br />
        <div>Obj1: <textarea id="obj1" name="obj1"></textarea></div>
        <br />
        The versions: <input id="versions" />
        <button id="getText">Get versions text(separate by comma)</button>
        <br />
        Versions Avalible:
        <div id="printVers"></div>
        <div id="finaltext"></div>
        <script>
            let orgText;
            let idobject;
            let idUser = '<%=Session["id"]%>';
            $("#getVers1").on("click", function(e) {
                $.ajax({
                    url:
                        "http://127.0.0.1:8080/api/con1/vers?id=" +
                        $("#idobjectinput").val(),
                    type: "GET"
                }).done(function(result) {
                    console.log(result);
                    $("#printVers").empty();
                    for (let i = 0; i < result.length; i++) {
                        $("#printVers").append(result[i] + ",");
                    }
                });
            });
            $("#getText").on("click", function(e) {
                let textGotten = $("#versions")
                    .val()
                    .split(",");
                idobject = $("#idobjectinput").val();
                let dataToSend = {
                    IDObject: idobject,
                    IDUser: idUser,
                    versions: textGotten
                };
                console.log(dataToSend);
                $.ajax({
                    url: "http://127.0.0.1:8080/api/con1/vers1",
                    type: "POST",
                    data: dataToSend
                }).done(function(result) {
                    console.log(result);
                    let text = "";
                    textGotten.forEach(e => {
                        result.forEach(k => {
                            if (k.IDVersion == e) {
                                if (k.IDUser == idUser)
                                    text +=
                                        '<font color="red">' +
                                        k.content +
                                        "</font>";
                                else text += k.content;
                            }
                        });
                    });
                    $("#finaltext").empty(text);
                    $("#finaltext").append(text);
                });
            });
            $("#getobjbtn1").on("click", function(e) {
                $.ajax({
                    url:
                        "http://127.0.0.1:8080/api/con1/obj?id=" +
                        $("#idobjectinput").val() +
                        "&idUser=" +
                        idUser,
                    type: "GET"
                }).done(function(result) {
                    console.log(result);
                    idobject = $("#idobjectinput").val();
                    $("#obj1").val(result.content);
                    orgText = result.content;
                });
                $("#obj1").on("change keyup paste", function() {
                    let newText = $("#obj1").val();
                    if (idobject) {
                        if (newText.length - orgText.length > 9) {
                            let toSend = "";
                            for (
                                let i = orgText.length;
                                i < newText.length;
                                ++i
                            )
                                toSend += newText[i];
                            let dataToSend = {
                                IDObject: idobject,
                                IDUser: idUser,
                                content: toSend
                            };
                            $.ajax({
                                url: "http://127.0.0.1:8080/api/con1/obj",
                                type: "PUT",
                                data: dataToSend
                            }).done(function(result) {
                                console.log(result);
                            });
                            orgText = newText;
                        }
                    }
                });
            });
        </script>
    </body>
</html>
