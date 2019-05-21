emptyCell = "";

function loadPuzzle() {
    pics = new Array(10);
    for (i = 1; i < 10; i++) {
        gasit = true;
        while (gasit == true) {
            x = 1 + (Math.floor(Math.random() * 1000) % 9);
            gasit = false;
            for (j = 1; j < i; j++) if (pics[j] == x) gasit = true;
        }
        pics[i] = x;
        // if (x == 10) emptyCell = i;
    }

    var cell;
    for (i = 1; i < 10; i++) {
        cell = document.getElementById(i);
        if (cell) {
            img = document.createElement("img");
            img.setAttribute("src", "img/" + pics[i] + ".jpg");
            cell.appendChild(img);
        }
    }
}
$(document).ready(function() {
    emptyCell = "";
    $("td").click(function() {
        if (emptyCell == "") emptyCell = cellID;
        if (cell2 == cellID) {
            emptyCell = "";
            return;
        }
        img = $(this).first;
        this.remove();
        this.append(img);
    });
    if (emptyCell == "") emptyCell = cellID;
    else {
        if (cell2 == cellID) {
            emptyCell = "";
            return;
        }
        emptyCell = "";
        console.log("ih");
        img1 = $("." + cell2.id + ":first-child");
        img = $("." + cell.id + ":first-child");
        cell1 = $("." + cell2.id);
        cell = $("." + cell.id);
        // console.log(cell);
        cell.remove(img);
        cell1.remove(img1);
        // cell1 = document.getElementById(cell2);
        // img1 = cell1.firstChild;
        // img = cell.firstChild;
        // cell.removeChild(cell.firstChild);
        // cell1.removeChild(cell1.firstChild);

        cell.append(img1);
        cell1.append(img);
    }
    if (check()) alert("You won");
});

function move(cellID, cell) {
    cell2 = emptyCell;
    if (emptyCell == "") emptyCell = cellID;
    else {
        if (cell2 == cellID) {
            emptyCell = "";
            return;
        }
        emptyCell = "";
        console.log("ih");
        img1 = $("." + cell2.id + ":first-child");
        img = $("." + cell.id + ":first-child");
        cell1 = $("." + cell2.id);
        cell = $("." + cell.id);
        // console.log(cell);
        cell.remove(img);
        cell1.remove(img1);
        // cell1 = document.getElementById(cell2);
        // img1 = cell1.firstChild;
        // img = cell.firstChild;
        // cell.removeChild(cell.firstChild);
        // cell1.removeChild(cell1.firstChild);

        cell.append(img1);
        cell1.append(img);
    }
    if (check()) alert("You won");
}

function check() {
    var table = document.getElementById("mytab1");
    var index = 1;
    for (var i = 0, row; (row = table.rows[i]); i++) {
        for (var j = 0, col; (col = row.cells[j]); j++) {
            let text = col.innerHTML;
            let slicedText = text.slice(14, text.lenght);
            let toCheck = index + '.jpg">';
            index++;
            if (toCheck != slicedText) return false;
        }
    }
    return true;
}
