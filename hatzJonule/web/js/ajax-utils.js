function getUserAssets(userid, callbackFunction) {
    $.getJSON(
        "UserInfoController",
        {action: 'getAll', userid: userid},
        callbackFunction
    );
}

function updateAsset(id, userid, description, value, callbackFunction) {
    $.get("AssetsController",
        {
            action: "update",
            id: id,
            userid: userid,
            description: description,
            value: value
        },
        callbackFunction
    );
}
function cleanTable() {
    $('#UserInfo')
        .find("tr:gt(0)")
        .remove();
}

function drawTable(data) {
    for (var i in data) {
        drawRow(data[i]);
    }
}

function drawRow(rowData) {
    var row = $("<tr />");
    // console.log(rowData.picture)
    $('#UserInfo').append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    row.append($("<td><img src=\"" + rowData.picture + "\"/></td>"));
    row.append($("<td>" + rowData.email + "</td>"));
    row.append($("<td>" + rowData.age + "</td>"));
    row.append($("<td>" + rowData.home_town + "</td>"));
}
