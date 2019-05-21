var page = 1,
    pagelimit = 2,
    totalrecord = 0;
getPaged();

$("#submitDataForm").on("submit", function() {
    var that = $(this),
        url = that.attr("action"),
        type = that.attr("method"),
        data = {};
    that.find("[name]").each(function(index, value) {
        var that = $(this),
            name = that.attr("name"),
            value = that.val();
        data[name] = value;
    });
    $.ajax({
        url: url,
        type: type,
        data: JSON.stringify(data),
        succes: function(responese) {
            console.log(responese);
        }
    });
    return false;
});

$("#hatz").on("click", function() {
    var url = "http://localhost:8080/server_reports/api/post/read.php",
        type = "GET";
    $.ajax({
        url: url,
        type: type
    }).done(function(data) {
        drawTable(data, "personDataTable");
    });
});

$("#deleteForm").on("submit", function() {
    var that = $(this),
        url = that.attr("action"),
        type = that.attr("method"),
        data = {};
    var id = that.find("[name]").val();
    data["id"] = id;
    $.ajax({
        url: url,
        type: type,
        data: JSON.stringify(data)
    }).done(function(result) {
        console.log(result);
    });
    return false;
});

$("#getBySeverity").on("submit", function() {
    var that = $(this),
        url = that.attr("action"),
        type = that.attr("method"),
        data = {};
    var id = that.find("[name]").val();
    data["severity"] = id;
    url = url + "?severity=" + id;
    $.ajax({
        url: url,
        type: type
    }).done(function(result) {
        console.log(result);
        drawTable(result, "by_severity");
    });
    return false;
});

$(".prev-btn").on("click", function() {
    if (page > 1) {
        page--;
    }
    getPaged(page);
});

$(".next-btn").on("click", function() {
    if (page * pagelimit > totalrecord) {
        page++;
    }
    getPaged(page);
});

function getPaged(pageNr) {
    $.ajax({
        url:
            "http://localhost:8080/server_reports/api/post/read_pagination.php",
        method: "GET",
        data: {
            page: pageNr,
            pagelimit: pagelimit
        },
        dataType: "json",
        success: function(data) {
            if (data.message) {
                page--;
            } else {
                drawTable(data, "personDataTable");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function cleanTable(tableName) {
    $(`#${tableName}`)
        .find("tr:gt(0)")
        .remove();
}

function drawTable(data, tableName) {
    cleanTable(tableName);
    for (var i in data) {
        drawRow(data[i], tableName);
    }
}

function drawRow(rowData, tableName) {
    var row = $("<tr />");
    $(`#${tableName}`).append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    row.append($("<td>" + rowData.id + "</td>"));
    row.append($("<td>" + rowData.type + "</td>"));
    row.append($("<td>" + rowData.severity + "</td>"));
    row.append($("<td>" + rowData.date + "</td>"));
    row.append($("<td>" + rowData.user + "</td>"));
    row.append($("<td>" + rowData.log + "</td>"));
}
