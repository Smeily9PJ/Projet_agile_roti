
<!DOCTYPE html>
<html>
<head>
	<title>MEETING TRACKER</title>
	<link rel="stylesheet" type="text/css" href="css/style_commun.css" />
    <link rel="stylesheet" type="text/css" href="css/style_etudiant.css" />
</head>
<body>
    <%@ include file="header.jsp" %>
    <div id="Etudiant_Attente">
    	<span id="Etudiant_Attente_Annonce">Prochain vote dans...</span>
		<br/>
		<span id="Etudiant_Attente_Chrono"></span>
		<br/><br/>
		<progress id="Etudiant_Attente_Loading" value="100" max="100"></progress>
	</div>
    <div id="Etudiant_Formulaire">
	<form method=POST action="">
		<img src="images/humeurs/vote_content.jpg" id="vote_content"/>
		<input type="range" min="1" max="5" step="1" value="3" id="Etudiant_valeurRange" onchange="miseAJourValeurRange()"/>
		<img src="images/humeurs/vote_pascontent.jpg" id="vote_mecontent"/>
		<br/>
		<span id="Etudiant_valeurVote">3</span> 
		<br/>
<!--		<input onClick="vote()" type=submit value="Voter" name="envoyer" id="Etudiant_validerVoter"/>   -->

	</form>
	<button class="btn btn-vote" onclick="vote()" id="Etudiant_Btn_Voter">Voter</button>
	</div>
	<script src="${pageContext.request.contextPath}/javascript/voteEtudiant.js" type="text/javascript"></script>
	
    <%@ include file="footer.jsp" %>
</body>
</html>