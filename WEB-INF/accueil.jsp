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
    <title>Le ROTI c'est la vie</title>
</head>
	<body>
		<%@ include file="header.jsp" %>
		<img src="images/modes/Create.jpg" id="accueil_create" onclick="create()"></img>		
		<img src="images/modes/Join.jpg" id="accueil_join" onclick="join()"></img>
		<div id="accueil_ClickCreate">
			<form METHOD="POST" ACTION="creer">
				<table><td>Numéro de session :</td></tr>
					   <td>#529871</td></tr>
					   <td>Mot de passe :</td></tr>
					   <td><input type=password name="accueil_text_mdpSession"/></td></tr>
					   <td><input id="Accueil_bouton_create" class="btn" type="submit" value="Creer" name="action"></td></table>	
			</form>
		</div>
		<div id="accueil_ClickJoin">
			<form METHOD="POST" ACTION="rejoindre">
			    <table><td>Numéro de session :</td></tr>
					   <td><input type=text name="accueil_text_idSession"></td></tr>
					   <td>Mot de passe :</td></tr>
					   <td><input type=password name="accueil_text_mdpSession"/></td></tr>
				       <td><input id="Accueil_bouton_join" class="btn" type="submit" value="Rejoindre" name="action"></td></table>
			</form>
		</div>
		<%@ include file="footer.jsp" %>
		
   <script src="${pageContext.request.contextPath}/javascript/accueil.js" type="text/javascript"></script>
	</body>
</html>