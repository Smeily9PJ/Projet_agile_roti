
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
	<div id="Emotions">
	<form METHOD="POST" ACTION="VoterHumeur">
		<input id="Emotion_colere" class="btn" type="submit" value='colere' name="action"  onclick="voteEmo(1)"/>
		<input id="Emotion_triste" class="btn" type="submit" value='triste' name="action"  onclick="voteEmo(2)"/>
		<input id="Emotion_blaze" class="btn" type="submit" value='blaze' name="action"  onclick="voteEmo(3)" />
		<input id="Emotion_dort" class="btn" type="submit" value='dort' name="action"  onclick="voteEmo(4)"/>
		<input id="Emotion_rigole" class="btn" type="submit" value='rigole' name="action" onclick="voteEmo(5)"/>
		<input id="Emotion_content" class="btn" type="submit" value='content' name="action" onclick="voteEmo(6)"/>
	</form>
	</div>
	<script src="${pageContext.request.contextPath}/javascript/voteEmotion.js" type="text/javascript"></script>
    <%@ include file="footer.jsp" %>
</body>
</html>