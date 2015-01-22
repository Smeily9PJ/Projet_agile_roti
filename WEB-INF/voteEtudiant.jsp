
<!DOCTYPE html>
<html>
<head>
	<title>Vote pour le ROTI</title>
	<link rel="stylesheet" type="text/css" href="css/style_commun.css" />
    <link rel="stylesheet" type="text/css" href="css/style_etudiant.css" />
</head>
<body>
    <header id="bandeau">
        <a href="accueil.html"><img src="${pageContext.request.contextPath}/images/header/testImageHeader.png" id="headerIcon"/></a>
    </header>
    <div id="Etudiant_Attente">
    	<span id="Etudiant_Attente_Annonce">Prochain vote dans...</span>
		<br/>
		<span id="Etudiant_Attente_Chrono">1 minutes et 00 secondes.</span>
		<br/><br/>
		<progress id="Etudiant_Attente_Loading" value="100" max="100"></progress>
	</div>
    <div id="Etudiant_Formulaire">
	<form method=POST action="">
		<input type="range" min="1" max="5" step="1" value="3" id="Etudiant_valeurRange" onchange="miseAJourValeurRange()"/>
		<br/>
		<span id="Etudiant_valeurVote">3</span> 
		<br/>
<!--		<input onClick="vote()" type=submit value="Voter" name="envoyer" id="Etudiant_validerVoter"/>   -->

	</form>
	<button onclick="vote()" id="Etudiant_validerVoter">Coucou</button>
	</div>
	<script src="${pageContext.request.contextPath}/javascript/voteEtudiant.js" type="text/javascript"></script>
	<footer id="pied">
        <p id="accueil_infos">qu'est-ce que c'est ? | credit</p>
     </footer>
</body>
</html>