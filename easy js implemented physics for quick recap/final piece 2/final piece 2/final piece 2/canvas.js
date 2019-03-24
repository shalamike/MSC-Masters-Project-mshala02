var ctx = null;
var canvas = null;  
var mousedownX = 0;
var mousedownY = 0;
var mouseupX = 0;
var mouseupY = 0;
var mouseX = 0;
var mouseY = 0;
var debugtxt = "";
var mouseisup = true;
var mouseisdown = false;
//var dt = 0.01;

$(document).ready(function () {
    window.setTimeout(dostart, 100);
	
	$(document).mousedown(function (e) {
		e.preventDefault();
		e.stopPropagation();
	
        if (e.which == 1) {
			var offset = $("#canvas").offset();
			mousedownX = Math.floor(e.pageX - offset.left);
			mousedownY = Math.floor(e.pageY - offset.top);
			mouseX = mousedownX;
			mouseY = mousedownY;
			mouseisup = false;
			mouseisdown = true;
			try { mouseDown(); } catch(err) { }
			//debugtxt = offset.left + " " + offset.top;
        }
    });
	$(document).mouseup(function (e) {
        if (e.which == 1) {
			var offset = $("#canvas").offset();
			mouseupX = Math.floor(e.pageX - offset.left);
			mouseupY = Math.floor(e.pageY - offset.top);
			mouseisup = true;
			mouseisdown = false;
			try { mouseUp(); } catch(err) { }
        }
    });
	$(document).mousemove(function(e) {
	    var offset = $("#canvas").offset();
	    mouseX = Math.floor(e.pageX - offset.left);
		mouseY = Math.floor(e.pageY - offset.top);
	});
	canvas = document.getElementById('canvas');
	ctx = canvas.getContext('2d');	
})

function dostart() {
	try { start(); } catch(err) { }
	window.setInterval(doloop, dt * 1000); 
}

function doloop() {
	try { loop(); } catch(err) { }
}

function clearScreen() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}

function drawRect(color, x, y, width, height) {
    ctx.strokeStyle = color;
    ctx.strokeRect(x, y, width, height);
}

function fillRect(color, x, y, width, height) {
    ctx.fillStyle = color;
    ctx.fillRect(x, y, width, height);
}

function drawImage(image, x, y, width, height) {
	ctx.drawImage(image, x, y, width, height); 
}	

function drawCircle(color, x, y, radius) {
    ctx.beginPath();
    ctx.arc(x, y, radius, 0, 2 * Math.PI, false);
    ctx.strokeStyle = color;
    ctx.stroke();
}

function fillCircle(color, x, y, radius) {
    ctx.beginPath();
    ctx.arc(x, y, radius, 0, 2 * Math.PI, false);
    ctx.fillStyle = color;
    ctx.fill();
}

function drawLine(color, startx, starty, endx, endy) {
	ctx.beginPath();
	ctx.moveTo(startx, starty);
	ctx.lineTo(endx, endy);
    ctx.strokeStyle = color;
	ctx.closePath();
	ctx.stroke();
}

function drawText(text, x, y) {
    ctx.fillStyle = "black";
	ctx.fillText(text, x, y);
}

function random(n) {
	return Math.floor(Math.random()*(n+1));
}

function randomcolor() {
	return "rgba(" + random(255) + "," + random(255) + "," + random(255) + "," + random(100)/100 + ")";
}

clearscreen = clearScreen;
drawrect = drawRect;
fillrect = fillRect;
drawimage = drawImage;
fillimage = drawImage;
fillImage = drawImage;
drawcircle = drawCircle;   
drawline = drawLine;      
fillcircle = fillCircle;
drawtext = drawText;
randomcolour = randomcolor;
try { mousedown = mouseDown; } catch(err) { } 
try { mouseup = mouseUp; } catch(err) { }
