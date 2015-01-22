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