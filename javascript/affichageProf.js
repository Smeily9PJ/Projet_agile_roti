var secondes = 0;
var minutes = 0;
var heures = 0;

function initialisation(){
	afficherBaseCourbe();
	chrono();
}

function chrono(){
	//lancer l'affichage de la courbe tous les intervalles données
	if(secondes%10 == 0){
		tracerCourbe();
	}
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


function afficherBaseCourbe(){
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
	      
	  context.beginPath();//On démarre un nouveau tracé

	  context.lineWidth = 10;
	  context.moveTo(0, 0);
      context.lineTo(0, 1000);
	  context.moveTo(0, 1000);
      context.lineTo(2000, 1000);
      context.stroke();//On trace seulement les lignes.
      context.closePath(); //fin
}

var xBase = 0;
var yBase = 0;
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
        
        //var xBase = 0 ; //abscisse du dernier point, temps du dernier vote
        //var yBase = 0; //ordonnée du dernier point, derniere moyenne enregistré
        var xDest = xBase+10; //abscisse du point a tracer, temps du vote en cours
        var yDest = yBase+10; //ordonnée du point a tracer, moyenne courante

        context.beginPath();//On démarre un nouveau tracé
        context.lineJoin = "round";
        context.lineCap = "round";
        context.strokeStyle = "blue";

        context.stroke();//On trace seulement les lignes.
        context.closePath(); //fin
        
        xBase = xDest;
        yBase = yDest;
}