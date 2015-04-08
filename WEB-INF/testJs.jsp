<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Votez ROTI">
    <meta name="keywords" content="Agile, méthodes agiles, SCRUM, ROTI, Conférence, Avis">
    <style type="text/css">
    	<%@ include file="../css/qunit-1.18.0.css" %>
    	<%@ include file="../css/testJs.css" %>
    </style>
    <title>QUnit test JS</title>
</head>
	<body >
		<div id="qunit"></div>
		<div id="qunit-fixture"></div>
		
		<div id="page_test">
			<%@include file="accueil.jsp"%>
			<%@include file="voteEtudiant.jsp"%>
			<%@include file="affichageProf.jsp"%>
		</div>
		
	   	<script src="${pageContext.request.contextPath}/javascript/qunit-1.18.0.js" type="text/javascript"></script>
	    <script src="${pageContext.request.contextPath}/javascript/tests.js" type="text/javascript"></script>
	</body>
</html>