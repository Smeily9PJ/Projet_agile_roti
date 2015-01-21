function miseAJourValeurRange(){
	var valeurAffiche = document.getElementById("voteEtudiant_valeurVote");
	var valeurAAffiche = document.getElementById("voteEtudiant_valeurRange");
	valeurAffiche.textContent = valeurAAffiche.value;
	
	function miseAJourValeurRange(vote) {

		  document.querySelector('#voteEtudiant_valeurVote').value = vote;

	}
}

