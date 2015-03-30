function miseAJourValeurRange(){
	var valeurAffiche = document.getElementById("Etudiant_valeurVote");
	var valeurAAfficher = document.getElementById("Etudiant_valeurRange");
	valeurAffiche.textContent = valeurAAfficher.value;
}

var secondes;
var minutes;
var SEC = 5;
var MIN = 0;
var chronometre;

function vote() {
	document.getElementById("Etudiant_Formulaire").style.display = "none";
	document.getElementById("Etudiant_Attente").style.display = "block";
	secondes=SEC;
	minutes=MIN;
	clearTimeout(chronometre);
	chronometre = setTimeout('chrono()',1000);
}

function chrono(){
	var bar = document.getElementById("Etudiant_Attente_Loading");
	var chrono = document.getElementById("Etudiant_Attente_Chrono");
	var newChrono ;
	var percent = (minutes*60 + secondes)*100 / (MIN*60 + SEC);
	bar.value = percent;
	if (secondes == 0){
		if (minutes == 0){
			document.getElementById("Etudiant_Formulaire").style.display = "block";
			document.getElementById("Etudiant_Attente").style.display = "none";
		}else{
			newChrono = minutes +" minutes et "+ secondes +" secondes.";
			secondes=59; minutes--;
		}
	}else{
		newChrono = minutes +" minutes et "+ secondes +" secondes.";
		secondes--;
	}
	chrono.textContent = newChrono;
	chronometre = setTimeout('chrono()',1000); //la fonction est relancée
}

var confirmOnLeave = function(msg) { 
    window.onbeforeunload = function (e) {
        e = e || window.event;
        msg = msg || '';
 
        // For IE and Firefox
        if (e) {e.returnValue = msg;}
 
        // For Chrome and Safari
        return msg;
    };
};
 
confirmOnLeave('Etes-vous sûr de vouloir quitter ?');