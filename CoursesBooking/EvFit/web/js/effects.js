function fadeIn(element, finalOpacity){
    var op = 0.1;  // initial opacity
    if (op >= finalOpacity)
    	return;
    
    var timer = setInterval(function () {
        if (op >= finalOpacity){
            clearInterval(timer);
            op = finalOpacity;
        }
        
        element.style.opacity = op;
        op += op * 0.1;
    }, 20);
    
}

function slideUp(element, startPosition, endPosition){
	if (endPosition > startPosition)
		return;

    var timer = setInterval(function () {
        if (startPosition <= endPosition){
            clearInterval(timer);
            startPosition = endPosition;
        }
        
        element.style.top = startPosition + '%';
        startPosition -= startPosition * 0.01;
        element.style.display = 'block';
    }, 10);
}