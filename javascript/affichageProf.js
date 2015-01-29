var secondes = 0;
var minutes = 0;
var heures = 0;

function initialisation(){
	afficherGraduationCourbe();
	chrono();
}

function chrono(){
	//lancer l'affichage de la courbe tous les intervalles données
	//if(secondes%10 == 0){
		tracerCourbe();
//	}
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




var numeroDeLHumeurAAfficher = 2;
function afficherLaBonneHumeur (){
	switch(numeroDeLHumeurAAfficher){
		case 1: document.getElementById("smiley").src='images/humeurs/sonne.png'; break;
		case 2: document.getElementById("smiley").src='images/humeurs/dodo.png'; break;
		default: document.getElementById("smiley").src='images/humeurs/sonne.png'; break;
	}
}


function afficherGraduationCourbe(){
	
	  //afficher les graduations
}

var xBase = 0;
var yBase = 400;
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
    
    	//a récup dans bdd
        var xDest = xBase+50; //abscisse du point a tracer, temps du vote en cours
        var yDest = yBase-5; //ordonnée du point a tracer, moyenne courante

        
		if(secondes >= 10){
			context.scale(0.75,1);
		}
        context.
        context.lineWidth = 3;
        context.lineJoin = "round";
        context.lineCap = "round";
        context.strokeStyle = "black";
        
        
        context.moveTo(xBase, yBase);
        context.lineTo(xDest,yDest);
        context.stroke();

        xBase = xDest;
        yBase = yDest;
}