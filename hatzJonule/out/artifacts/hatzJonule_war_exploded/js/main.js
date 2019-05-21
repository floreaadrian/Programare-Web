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
    $('#UserInfo').append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    row.append($("<td><img src=\"" + rowData.picture + "\"/></td>"));
    row.append($("<td>" + rowData.email + "</td>"));
    row.append($("<td>" + rowData.age + "</td>"));
    row.append($("<td>" + rowData.home_town + "</td>"));
}
