var secondes = 0;
var minutes = 0;
var heures = 0;
var numeroPoint = 0;
var abscissePointsDejaTrace = [0];
var ordonneePointsDejaTrace = [400];

function chrono(){
	tracerCourbe();
	var chrono = document.getElementById("affichageProf_chono");
	var newChrono ;
	if (secondes == 59){
		if (minutes == 59){
			secondes=0;minutes=0;heures++;
		}else{
			secondes=0; minutes++;
		}
	}else{
		secondes++;
	}
	newChrono = heures + " : " + minutes + " : "  + secondes;
	chrono.textContent = newChrono;
	setTimeout('chrono()',1000) //la fonction est relancée
	
}


function tracerCourbe(){
    var canvas = document.getElementById('affichageProf_canvas');
    if(!canvas)
    {
        alert("Impossible de récupérer le canvas");
        return;
    }

    var context = canvas.getContext('2d');
    if(!context)
    {
        alert("Impossible de récupérer le context du canvas");
        return;
    }


	context.clearRect(1,2,700, 400);
	context.clearRect(0,0,700, 400);
    context.lineWidth = 3;
    context.lineJoin = "round";
    context.lineCap = "round";
    context.strokeStyle = "black";
		if(numeroPoint%10 == 0){
			context.clearRect(0,0,700, 400);
			context.beginPath();
			for(var i =1; i <= numeroPoint; i++){
				if(abscissePointsDejaTrace[i]-2 > abscissePointsDejaTrace[i-1]){
					abscissePointsDejaTrace[i] -= 2;
				}
				context.moveTo(abscissePointsDejaTrace[i-1], ordonneePointsDejaTrace[i-1]);
		        context.lineTo(abscissePointsDejaTrace[i], ordonneePointsDejaTrace[i]);
			}
		}
		var newX = abscissePointsDejaTrace[numeroPoint]+10; //a récuperer dans bdd
		var newY = 350; //a récuperer dans bdd
		context.moveTo(abscissePointsDejaTrace[numeroPoint], ordonneePointsDejaTrace[numeroPoint]);
	    context.lineTo(newX,newY);
	    numeroPoint += 1;
	    abscissePointsDejaTrace.push(newX);
	    ordonneePointsDejaTrace.push(newY);
        context.stroke();
        context.closePath();
}