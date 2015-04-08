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

    <body onload="startChrono()">
		
		<%@ include file="header.jsp" %>
		
		<div class="DIV_BODDY">
			<div class="PARTIE_GAUCHE">
				<div class="INFORMATION_LABEL">
					
						<label>Id session : <b><%= session.getAttribute("identifiant") %></b></label>
						<br />
						<label>Mdp session : <b><%= session.getAttribute("mdp") %></b></label>
						<br />
						<label>Personnes connectées : <b id="nbPersonne">?</b></label>
						<br />
						<label>Avis moyen : <b id="avisMoyen">?</b></label>
						<br />
						<label>Timing vote : <b id="timingVote"><%= session.getAttribute("timing") %></b></label>						
						<br /><br />
						<label>Humeur majoritaire :</label>	
						<br /><br />
						<!-- <label>Humeur g&eacute;n&eacute;rale :</label>	 -->

				</div>
				<div class="IMAGE_HUMEUR_MAJ">
					<img name="humeurMajoritaire" id="humeurMajoritaire" width="50" height="50" src="images/humeurs/interrogation.jpg" />	
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
					<button onclick="pause()" class="btn btn-pause" id="pause" >Pause</button>
					<button onclick="startChrono()" class="btn btn-reprise" id="reprise" >Reprise</button>
				</div>
			</div>
			<div id="bgCanvas">
			<canvas id="affichageProf_canvas" width="700" height="400">Navigateur pas à jour pour afficher la courbe, veuillez mettre votre navigateur à jour.</canvas> 
			</div>
		</div>
		
		<%@ include file="footer.jsp" %>
		<script src="${pageContext.request.contextPath}/javascript/affichageProf.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/javascript/securiteFermeture.js" type="text/javascript"></script>
		
    </body>
</html>