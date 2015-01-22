function miseAJourValeurRange(){
	var valeurAffiche = document.getElementById("Etudiant_valeurVote");
	var valeurAAffiche = document.getElementById("Etudiant_valeurRange");
	valeurAffiche.textContent = valeurAAffiche.value;
	
	function miseAJourValeurRange(vote) {
		  document.querySelector('#Etudiant_valeurVote').value = vote;
	}
	
}

function vote() {
	document.getElementById("Etudiant_Formulaire").style.display = "none";
	document.getElementById("Etudiant_Attente").style.display = "block";
	chrono();
}

var secondes=20
var minutes=0

function chrono(){
	var chrono = document.getElementById("Etudiant_Attente_Chrono");
	var newChrono ;
	if (secondes == 0){
		if (minutes == 0){
			newChrono = "Maintenant !";
			document.getElementById("Etudiant_Formulaire").style.display = "block";
			document.getElementById("Etudiant_Attente").style.display = "none";
		}else{
			secondes=59; minutes--;
			newChrono = minutes +" minutes et "+ secondes +"secondes.";
		}
	}else{
		secondes--;
		newChrono = minutes +" minutes et "+ secondes +"secondes.";
	}
	chrono.textContent = newChrono;
	setTimeout('chrono()',1000) //la fonction est relancée
}
