var f1 = document.getElementById("f1");
var f2 = document.getElementById("f2");
var f3 = document.getElementById("f3");
var f4 = document.getElementById("f4");
var f5 = document.getElementById("f5");
var f6 = document.getElementById("f6");
var f7 = document.getElementById("f7");
var f8 = document.getElementById("f8");
var f9 = document.getElementById("f9");
var moving = false;
f1.addEventListener("mousedown", initialClick, false);
f2.addEventListener("mousedown", initialClick, false);
f3.addEventListener("mousedown", initialClick, false);
f4.addEventListener("mousedown", initialClick, false);
f5.addEventListener("mousedown", initialClick, false);
f6.addEventListener("mousedown", initialClick, false);
f7.addEventListener("mousedown", initialClick, false);
f8.addEventListener("mousedown", initialClick, false);
f9.addEventListener("mousedown", initialClick, false);

function getElPos(el) {
    if (el == "f1" || el == "f2" || el == "f3" || el == "f4") {
        var rect = document.getElementById(el).getBoundingClientRect();
        return rect;
    }
}

function verify() {
    let contor = 0;
    let rectF1 = getElPos("f1");
    let rectF2 = getElPos("f2");
    let rectF3 = getElPos("f3");
    let rectF4 = getElPos("f4");
    if (
        rectF1.right - rectF2.left <= 2 &&
        Math.abs(rectF1.top - rectF2.top) <= 2
    )
        contor++;
    if (
        Math.abs(rectF1.left - rectF3.left) <= 2 &&
        Math.abs(rectF3.bottom - rectF4.bottom) <= 2
    )
        contor++;
    if (Math.abs(rectF4.right - rectF2.right) <= 2) contor++;
    if (contor == 3) console.log("win");
}

function move(e) {
    let id = e.path[1].id;
    var newX = e.clientX - 10;
    var newY = e.clientY - 10;
    image.style.left = newX + "px";
    image.style.top = newY + "px";
    verify();
}

function initialClick(e) {
    if (moving) {
        document.removeEventListener("mousemove", move);
        moving = !moving;
        return;
    }
    moving = !moving;
    image = this;
    document.addEventListener("mousemove", move, false);
}
