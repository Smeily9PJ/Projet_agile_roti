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
    <script type="text/javascript">
        function create(){
            document.getElementById('accueil_create').style.display = 'none';
            document.getElementById('accueil_ClickCreate').style.display = 'block';
            if(document.getElementById('accueil_join').style.display == 'none')
                document.getElementById('accueil_ClickJoin').style.display = 'none';
                document.getElementById('accueil_join').style.display = 'block';
        }
        function join(){
            document.getElementById('accueil_join').style.display = 'none';
            document.getElementById('accueil_ClickJoin').style.display = 'block';
            if(document.getElementById('accueil_create').style.display == 'none')
                document.getElementById('accueil_ClickCreate').style.display = 'none';
                document.getElementById('accueil_create').style.display = 'block';
        }
    </script>
</head>
	<body>
		<header id="bandeau">
			<a href="accueil.html"><img src="images/header/testImageHeader.png" id="headerIcon"/></a>
	    </header>
		<img src="images/modes/Create.jpg" id="accueil_create" onclick="create()"></img>		
		<img src="images/modes/Join.jpg" id="accueil_join" onclick="join()"></img>
		<div id="accueil_ClickCreate">
			<form METHOD="POST" ou "GET" ACTION="">
				<table><td>Numéro de session :</td></tr>
					   <td>#529871</td></tr>
					   <td>Mot de passe :</td></tr>
					   <td><input type=password name="accueil_text_mdpSession"/></td></tr>
					   <td><input type="submit" value="Creer"></td></table>	
			</form>
		</div>
		<div id="accueil_ClickJoin">
			<form METHOD="POST" ou "GET" ACTION="">
			    <table><td>Numéro de session :</td></tr>
					   <td><input type=text name="accueil_text_idSession"></td></tr>
					   <td>Mot de passe :</td></tr>
					   <td><input type=password name="accueil_text_mdpSession"/></td></tr>
				       <td><input type="submit" value="Rejoindre"></td></table>
			</form>
		</div>
		<footer id="pied">
           <p id="accueil_infos">qu'est-ce que c'est ? | credit</p>
        </footer>
	</body>
</html>