listOfPics = ["pic1", "pic2", "pic3", "pic4"];
$(document).ready(function() {
    // for (let i = 0; i < 4; i++) {
    //     $("#" + listOfPics[i]).animate({ left: "-=300" }, 1500, "swiing");
    // }
    function beeLeft() {
        let toGoLeft = "-=" + $("#pic1").offset().left;
        $("#pic1").animate({ left: toGoLeft }, 1500, "swing", beeRight);
    }
    function beeRight() {
        let toGoRight = "+=" + ($(window).width() - 500);
        $("#pic1").animate({ left: toGoRight }, 1500, "swing", beeLeft);
    }
    beeRight();
    console.log($(window).width());
});

("use strict");

$(function() {
    //settings for slider
    var width = 720;
    var animationSpeed = 1000;
    var pause = 3000;
    var currentSlide = 1;

    //cache DOM elements
    var $slider = $("#slider");
    var $slideContainer = $(".slides", $slider);
    var $slides = $(".slide", $slider);

    var interval;

    function startSlider() {
        interval = setInterval(function() {
            $slideContainer.animate(
                { "margin-left": "-=" + width },
                animationSpeed,
                function() {
                    if (++currentSlide === $slides.length) {
                        currentSlide = 1;
                        $slideContainer.css("margin-left", 0);
                    }
                }
            );
        }, pause);
    }
    function pauseSlider() {
        clearInterval(interval);
    }

    $slideContainer.on("mouseenter", pauseSlider).on("mouseleave", startSlider);

    startSlider();
});
