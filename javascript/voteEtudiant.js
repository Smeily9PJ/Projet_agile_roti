function miseAJourValeurRange(){
	var valeurAffiche = document.getElementById("Etudiant_valeurVote");
	var valeurAAffiche = document.getElementById("Etudiant_valeurRange");
	valeurAffiche.textContent = valeurAAffiche.value;
	
	function miseAJourValeurRange(vote) {
		  document.querySelector('#Etudiant_valeurVote').value = vote;
	}
	
}

var secondes
var minutes
var SEC = 5 // Variables depart
var MIN = 0// Variables depart
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

function voteEmo() {
	document.getElementById("Etudiant_Emotions").style.display = "none";
	window.alert("Votre emotion a ete enregistree !");
}
