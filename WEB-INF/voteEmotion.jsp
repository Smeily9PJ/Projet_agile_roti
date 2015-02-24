
<!DOCTYPE html> 
<html>
<head>
	<title>MEETING TRACKER</title>
	<link rel="stylesheet" type="text/css" href="css/style_commun.css" />
    <link rel="stylesheet" type="text/css" href="css/style_emotion.css" />
</head>
<body> <!-- mettre param java pour récup intervalle dans bdd -->
    <%@ include file="header.jsp" %>
    <span>Donnez votre émotion !</span>
	<div id="Emotions" onclick="voteEmo()">
	<form METHOD="POST" ACTION="">
		<input id="Emotion_colere" class="btn" type="image" name="action" src="images/humeurs/humeur_colere.gif" />
		<input id="Emotion_triste" class="btn" type="image" name="action" src="images/humeurs/humeur_triste.gif"/>
		<input id="Emotion_blaze" class="btn" type="image" name="action" src="images/humeurs/humeur_blaze.gif"/>
		<input id="Emotion_dort" class="btn" type="image" name="action" src="images/humeurs/humeur_dort.gif"/>
		<input id="Emotion_rigole" class="btn" type="image" name="action" src="images/humeurs/humeur_rigole.gif"/>
		<input id="Emotion_content" class="btn" type="image" name="action" src="images/humeurs/humeur_content.gif"/>
	</form>
	</div>
	<script src="${pageContext.request.contextPath}/javascript/voteEmotion.js" type="text/javascript"></script>
    <%@ include file="footer.jsp" %>
</body>
</html>