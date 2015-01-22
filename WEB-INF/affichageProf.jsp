<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <style type="text/css">
	    	<%@ include file="../css/style_commun.css" %><%@ include file="../css/style_prof.css" %>
	    </style>
        <title>Le ROTI c'est la vie</title>
    </head>

    <body onload="chrono()">
		
		<%@ include file="header.jsp" %>
		
		<div class="DIV_BODDY">
			<div class="PARTIE_GAUCHE">
				<div class="INFORMATION_LABEL">
					
						<label>Session :</label>
						<br />
						<label>Personnes connect&eacute;es :</label>
						<br />
						<label>Avis moyen :</label>					
						<br /><br />
						<!-- <label>Humeur g&eacute;n&eacute;rale :</label>	 -->

				</div>
				<div class="RECEPTION_INFORMATION">
					<input type="text" name="idSession" id="idSession" readonly />
					<br />
					<input type="text" name="nbPersonne" id="nbPersonne" readonly />
					<br />
					<input type="text" name="avisMoyen" id="avisMoyen" readonly />
					<br />
					<!-- <img id="smiley" src="images/humeurs/sonne.png" onclick="afficherLaBonneHumeur();" alt="Smiley" width=40 height=40 /> -->
				</div>
				<br>
				<p id="affichageProf_chono"> 0 : 0 : 0 </p>
				<div class="BOUTONS">
					<form METHOD="POST" ACTION="accueil">
						<!-- <input class="BOUTON" type="submit" value="Lancer vote" name="lancerVote" id="lancerVote" /> -->
						<br />
						<input class="BOUTON" type="submit" value="Fin de session" name="action" id="finDeSession" >
					</form>
				</div>
			</div>
			<div class="PARTIE_DROITE">
				<p class="MONCADRE"></p>
				<canvas id="affichageProf_canvas" width="600" height="600">Navigateur pas à jour pour afficher la courbe, veuillez mettre votre navigateur.</canvas> 
			</div>
		</div>
		
		<%@ include file="footer.jsp" %>
		
		<script src="${pageContext.request.contextPath}/javascript/affichageProf.js" type="text/javascript"></script>
    </body>
</html>