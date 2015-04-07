var valeur;

/*
 * accueil
 */
QUnit.test( "accueil_create", function( assert ) {
	create();
	valeur = (document.getElementById('accueil_create').style.visibility == 'hidden' );
		valeur = (valeur && document.getElementById('accueil_ClickCreate').style.visibility == 'visible');
		valeur = (valeur && document.getElementById('accueil_join').style.visibility == 'visible');
		valeur = (valeur && document.getElementById('accueil_ClickJoin').style.visibility == 'hidden');
	assert.equal(valeur, true);
});
QUnit.test( "accueil_join", function( assert ) {
	join();
	valeur = (document.getElementById('accueil_create').style.visibility == 'visible') ;
		valeur = (valeur && document.getElementById('accueil_ClickCreate').style.visibility == 'hidden');
		valeur = (valeur && document.getElementById('accueil_join').style.visibility == 'hidden');
		valeur = (valeur && document.getElementById('accueil_ClickJoin').style.visibility == 'visible');
	assert.equal(valeur, true);
});

/*
 * etudiant
 */
QUnit.test( "etudiant_modifRange", function( assert ) {
	document.getElementById("Etudiant_valeurRange").value = 4;
	miseAJourValeurRange();
	assert.equal(document.getElementById("Etudiant_valeurVote").textContent, 4);
});
QUnit.test( "etudiant_vote", function( assert ) {
	vote();
	valeur = (document.getElementById("Etudiant_Formulaire").style.display == "none");
	valeur = (valeur && document.getElementById("Etudiant_Attente").style.display == "block");
	valeur = (valeur && SEC == 5 && MIN == 0);
	assert.equal(valeur, true);
});
QUnit.test( "etudiant_chrono", function( assert ) {
	secondes=SEC;
	minutes=MIN;
	chrono();
	valeur = (document.getElementById("Etudiant_Attente_Chrono").textContent == "0 minutes et 5 secondes.");
	setTimeout('verifierChrono()',1000); 
	assert.equal(valeur, true);
});

function verifierChrono(){
	valeur = (valeur && document.getElementById("Etudiant_Attente_Chrono").textContent == "0 minutes et 4 secondes.");
}


/*
 * professeur
 */
QUnit.test( "professeur_baseChrono", function( assert ) {
	assert.equal(etatchronoProf, 0);
});
QUnit.test( "professeur_pause", function( assert ) {
	pause();
	assert.equal(etatchronoProf, 0);
});
QUnit.test( "professeur_start", function( assert ) {
	startChrono();
	assert.equal(etatchronoProf, 1);
});
QUnit.test( "professeur_pauseStart", function( assert ) {
	pause();
	valeur = (etatchronoProf == 0);
	startChrono();
	valeur = (valeur && etatchronoProf == 1);
	assert.equal(valeur, true);
});
QUnit.test( "professeur_increment", function( assert ) {
	incrementerchronoProf();
	assert.equal(document.getElementById("affichageProf_chono").textContent, "0 : 0 : 7");
});