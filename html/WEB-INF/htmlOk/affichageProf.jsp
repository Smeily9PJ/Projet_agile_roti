<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <style type="text/css">
    <%@ include file="/WEB-INF/css/style_commun.css" %><%@ include file="/WEB-INF/css/affichageProf.css" %>
    </style>
        <title>AffichageProf</title>
    </head>

    <body onload="horlogeFonction()">
		
		<header id="bandeau">
			<a href="accueil.html"><img src="images/header/testImageHeader.png" id="headerIcon"/></a>
		</header>
		<div class="DIV_BODDY">
			<h1>Votre courbe</h1>	
			<div class="PARTIE_HAUT">
				<div class="INFORMATION_LABEL">
					<form method="post" action="affichageProf.html">
						<label>Session :</label>
						<br />
						<label>Personnes connect&eacutees :</label>
						<br />
						<label>Avis moyen :</label>					
						<br /><br />
						<label>Humeur g&eacuten&eacuterale :</label>	
					</form>
				</div>
				<div class="RECEPTION_INFORMATION">
					<input type="text" name="idSession" id="idSession" readonly />
					<br />
					<input type="text" name="nbPersonne" id="nbPersonne" readonly />
					<br />
					<input type="text" name="avisMoyen" id="avisMoyen" readonly />
					<br />
					<img id="smiley" src="images/humeurs/sonne.png" onclick="afficherLaBonneHumeur();" alt="Smiley" width=40 height=40 />
				</div>
				<div class="BOUTONS">
					<form method="post" action="affichageProf.html">
						<input class="BOUTON" type="submit" value="Lancer vote" name="lancerVote" id="lancerVote" />
						<br />
						<input class="BOUTON" type="submit" value="Fin de session" name="finDeSession" id="finDeSession" />
					</form>
				</div>
			</div>
			<div class="SPACER"><hr /></div>
			
			
			<div>
				<p class="COURBE">graph a faire en java</p>
			</div>
		
		</div>
		
		<script src="humeur.js" type="text/javascript" src="javascript.js"></script>
    </body>
</html>