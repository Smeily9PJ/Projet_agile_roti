<!DOCTYPE html>
<html>
<head>
	<title>Vote pour le ROTI</title>
	<link rel="stylesheet" type="text/css" href="css/css.css" />
</head>
<body onload="chrono()">
	<span id="affichageEtudiant_tempsProchainVote">Prochain vote dans :</span>
	<br/>
	<span id="chrono">1 minutes et 00 secondes.</span>
	<br/><br/>
	<form method=POST action=voteEtudiant.html>
		<input id="affichageEtudiant_voter" type=submit value="voter" name="voter" id="bouton"/>
	</form>
	
	<script src="affichageEtudiant.js" type="text/javascript"></script>
</body>
</html>