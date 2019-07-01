<%-- Created by IntelliJ IDEA. User: Jango Date: 24-Jun-19 Time: 3:49 PM To
change this template use File | Settings | File Templates. --%>
<%@ page
        contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="js/jquery-2.0.3.js"></script>
    <script>
        window.onload = function () {
            canvas = document.getElementById("canvasArea");
            context = canvas.getContext("2d");

            var numCircles = 3;
            var maxRadius = 15;
            var minRadius = 15;
            var colors = [
                "aqua",
                "black",
                "blue",
                "fuchsia",
                "green",
                "cyan",
                "lime",
                "maroon",
                "navy",
                "olive",
                "purple",
                "red",
                "silver",
                "teal",
                "yellow",
                "azure",
                "gold",
                "bisque",
                "pink",
                "orange"
            ];
            var numColors = colors.length;

            // A3. CREATE circles.
            for (var n = 0; n < numCircles; n++) {
                // A4. RANDOM values for circle characteristics.
                var xPos = Math.random() * canvas.width;
                var yPos = Math.random() * canvas.height;
                var radius =
                    minRadius + Math.random() * (maxRadius - minRadius);
                var colorIndex = Math.random() * (numColors - 1);
                colorIndex = Math.round(colorIndex);
                var color = colors[colorIndex];

                // A5. DRAW circle.
                drawCircle(context, xPos, yPos, radius, color);
            }
        };
        $.ajax({
            url: "GetCircle",
            method: "GET"
        }).done(function (result) {
            console.log(result);
        });

        function drawCircle(context, xPos, yPos, radius, color) {
            //B1. PARAMETERS for shadow and angles.
            var startAngle = (Math.PI / 180) * 0;
            var endAngle = (Math.PI / 180) * 360;
            context.shadowColor = "gray";
            context.shadowOffsetX = 1;
            context.shadowOffsetY = 1;
            context.shadowBlur = 5;

            //B2. DRAW CIRCLE
            context.beginPath();
            context.arc(xPos, yPos, radius, startAngle, endAngle, false);
            context.fillStyle = color;
            context.fill();
        }
    </script>
    <title>Success page</title>
</head>
<body>
<div
        style="width:500px;   height:150px;
                 margin:0 auto; padding:5px;"
>
    <canvas
            id="canvasArea"
            width="500"
            height="550"
            style="border:2px solid black"
    >
    </canvas>
</div>
</body>
</html>
