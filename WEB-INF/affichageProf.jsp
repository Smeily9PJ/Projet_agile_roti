<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <style type="text/css">
	    	<%@ include file="../css/style_commun.css" %><%@ include file="../css/style_prof.css" %>
	    </style>
        <title>MEETING TRACKER</title>
    </head>

    <body onload="chrono()">
		
		<%@ include file="header.jsp" %>
		
		<div class="DIV_BODDY">
			<div class="PARTIE_GAUCHE">
				<div class="INFORMATION_LABEL">
					
						<label>Id session :</label>
						<br />
						<label>Mdp session :</label>
						<br />
						<label>Personnes connectées :</label>
						<br />
						<label>Avis moyen :</label>	
						<br />
						<label>Timing vote :</label>						
						<br /><br />
						<!-- <label>Humeur g&eacute;n&eacute;rale :</label>	 -->

				</div>
				<div class="RECEPTION_INFORMATION">
					<input type="text" name="idSession" id="idSession" value="<%= session.getAttribute("identifiant") %>" readonly />
					<br />
					<input type="text" name="mdpSession" id="mdpSession" value="<%= session.getAttribute("mdp") %>" readonly />
					<br />
					<input type="text" name="nbPersonne" id="nbPersonne" value="xx" readonly />
					<br />
					<input type="text" name="avisMoyen" id="avisMoyen" value="x" readonly />
					<br />
					<input type="text" name="timingVote" id="timingVote" value="x" readonly />
					<!-- <img id="smiley" src="images/humeurs/sonne.png" onclick="afficherLaBonneHumeur();" alt="Smiley" width=40 height=40 /> -->
				</div>
				<br>
				<p id="affichageProf_chono"> 0 : 0 : 0 </p>
				<div class="BOUTONS">
					<form METHOD="POST" ACTION="accueil">
						<!-- <input class="BOUTON" type="submit" value="Lancer vote" name="lancerVote" id="lancerVote" /> -->
						<br />
						<input class="btn btn-finSession" type="submit" value="Fin de session" name="action" id="finDeSession" >
					</form>
				</div>
			</div>
			<div id="bgCanvas">
			<canvas id="affichageProf_canvas" width="700" height="400">Navigateur pas à jour pour afficher la courbe, veuillez mettre votre navigateur.</canvas> 
			</div>
		</div>
		
		<%@ include file="footer.jsp" %>
		<script src="${pageContext.request.contextPath}/javascript/affichageProf.js" type="text/javascript"></script>
		
    </body>
</html>