<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <style type="text/css">
	    	<%@ include file="../css/style_commun.css" %><%@ include file="../css/style_prof.css" %>
	    </style>
        <title>Le ROTI c'est la vie</title>
    </head>

    <body>
		
		<%@ include file="header.jsp" %>
		
		
		<br/><br/><br/><br/><br/><br/>
		<div>Id de session : "id à récuperer avec variable de session"<br/>
		Mdp de session : "mdp à récuperer avec variable de session"</div><br/>
		
		<div>Session commencée depuis : <b id="affichageProf_chrono">chrono</b><br/>
			<form METHOD="POST" ACTION="accueil">
				<input type="submit" value="Fin de session" name="action">	
			</form>
		</div><br/>
		<div id="affichageProf_courbe">une courbe</div>
		
		
		<%@ include file="footer.jsp" %>
		
		<script src="${pageContext.request.contextPath}/javascript/affichageProf.js" type="text/javascript"></script>
    </body>
</html>