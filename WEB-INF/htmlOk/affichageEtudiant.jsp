
<!DOCTYPE html>
<html onload="chrono()">
	<head>
		<title>Vote pour le ROTI</title>
		<style type="text/css">
	    <%@ include file="../css/style_commun.css" %><%@ include file="../css/style_etudiant.css" %>
	    </style>
	</head>
	<body onload="chrono()">
		<span id="affichageEtudiant_tempsProchainVote">Prochain vote dans :</span>
		<br/>
		<span id="chrono">1 minutes et 00 secondes.</span>
		<br/><br/>
		<form method=POST action="<c:url value="/WEB-INF/voteEtudiant.jsp"/>">
			<input id="affichageEtudiant_voter" type=submit value="voter" name="voter" id="bouton"/>
		</form>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/affichageEtudiant.js"></script>
	</body>
</html>