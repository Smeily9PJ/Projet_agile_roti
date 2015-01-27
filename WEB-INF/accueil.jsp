<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Votez ROTI">
    <meta name="keywords" content="Agile, méthodes agiles, SCRUM, ROTI, Conférence, Avis">
    <style type="text/css">
    <%@ include file="../css/style_commun.css" %><%@ include file="../css/style_accueil.css" %>
    </style>
    <title>MEETING TRACKER</title>
</head>
	<body>
		<%@ include file="header.jsp" %>
		<div id="accueil_create" class="hi-icon-wrap hi-icon-effect-1 hi-icon-effect-1a">
			<a onclick="create()" class="hi-icon hi-icon-mobile"><img src="images/modes/Create.jpg"/></a>
		</div>		
		<div id="accueil_join" class="hi-icon-wrap hi-icon-effect-1 hi-icon-effect-1a">
			<a onclick="join()" class="hi-icon hi-icon-mobile"><img src="images/modes/Join.jpg"/></a>
		</div>
		<div id="accueil_ClickCreate">
			<form METHOD="POST" ACTION="creer">
				<table><tr><td>Numéro de session :</td></tr>
					   <tr><td>#529871</td></tr>
					   <tr><td>Mot de passe :</td></tr>
					   <tr><td><input type=password name="accueil_text_mdpSession"/></td></tr></table>	
				<input id="Accueil_bouton_create" class="btn" type="submit" value="Creer" name="action"/>
			</form>
		</div>
		<div id="accueil_ClickJoin">
			<form METHOD="POST" ACTION="rejoindre">
			    <table><tr><td>Numéro de session :</td></tr>
					   <tr><td><input type=text name="accueil_text_idSession"></td></tr>
					   <tr><td>Mot de passe :</td></tr>
					   <tr><td><input type=password name="accueil_text_mdpSession"/></td></tr></table>
				<input id="Accueil_bouton_join" class="btn" type="submit" value="Rejoindre" name="action"/>
			</form>
		</div>	
		<%@ include file="footer.jsp" %>
		
   <script src="${pageContext.request.contextPath}/javascript/accueil.js" type="text/javascript"></script>
	</body>
</html>