var secondes = 0;
var minutes = 0;
var heures = 0;

function chrono(){
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


/*window.onload = function()
{
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
        
        var xBase = 0 ; //a récuperer
        var yBase = 300; //a récuperer aussi
        var yDest = 100;
        var xDest = 100;
        var taille = 600;

        context.beginPath();//On démarre un nouveau tracé
        context.moveTo(xBase, yBase);//On se déplace au coin inférieur gauche
        context.lineTo(0, taille);
        context.lineTo(0, taille);
        context.lineTo(-taille, 0);
        context.lineTo(0, -taille);
        context.stroke();//On trace seulement les lignes.
        context.closePath();
}*/