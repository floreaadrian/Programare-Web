var container = document.getElementById("container");

for (var i = 1; i < 5; i++) {
    var img = document.createElement("img");
    var src = `img/${i}.jpg`;
    img.src = src;
    container.appendChild(img);
}
var cloneImg1 = document.images[0].cloneNode(false);
var cloneImg2c1 = document.images[1].cloneNode(false);
var cloneImg2c2 = document.images[1].cloneNode(false);
var cloneImg3c1 = document.images[2].cloneNode(false);
var cloneImg3c2 = document.images[2].cloneNode(false);
var cloneImg4c1 = document.images[3].cloneNode(false);
var cloneImg4c2 = document.images[3].cloneNode(false);
var cloneImg5 = document.images[4].cloneNode(false);

container.insertBefore(cloneImg5, document.images[0]);
container.insertBefore(cloneImg4c1, document.images[0]);
container.insertBefore(cloneImg3c1, document.images[0]);
container.insertBefore(cloneImg2c1, document.images[0]);
container.appendChild(cloneImg1);
container.appendChild(cloneImg2c2);
container.appendChild(cloneImg3c2);
container.appendChild(cloneImg4c2);

// Step 3: Adding an infinite scroll effect
var sliderStartForward = document.images[4].getBoundingClientRect().left;
var sliderEndForward = document.images[8].getBoundingClientRect().right - 10;
var sliderStartBackward = document.images[4].getBoundingClientRect().right;

// We're repositionning our slider to our first true image
// as currently the first image we're seing is a clone
container.scrollLeft = sliderStartForward;

container.addEventListener("scroll", scrolling);

function scrolling() {
    // We're sliding backwards and reached the end
    if (container.scrollLeft < 1) {
        container.scrollLeft = sliderStartBackward;
    }

    // We're sliding forwards and reached the end
    if (container.scrollLeft > sliderEndForward) {
        container.scrollLeft = sliderStartForward;
    }
}
