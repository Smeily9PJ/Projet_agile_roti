var secondes = 0;
var minutes = 0;
var heures = 0;
var numeroPoint = 0;
var abscissePointsDejaTrace = [ 0 ];
var ordonneePointsDejaTrace = [];
var moyenne = 3;
var nbPersonnes = 0;

var etatchronoProf = 0; // 0 = arret, 1 = marche

function chronoProf() {
	if (etatchronoProf == 1) {
		incrementerchronoProf();
		majPage();
		setTimeout('chronoProf()', 1000) // la fonction est relancée
	}
}

function incrementerchronoProf(){
	var chronoProf = document.getElementById("affichageProf_chono");
	var newchronoProf;
	if (secondes == 59) {
		if (minutes == 59) {
			secondes = 0;
			minutes = 0;
			heures++;
		} else {
			secondes = 0;
			minutes++;
		}
	} else {
		secondes++;
	}
	newchronoProf = heures + " : " + minutes + " : " + secondes;
	chronoProf.textContent = newchronoProf;
}

function majPage(){
	var timing = document.getElementById("timingVote");
	var valueTiming = timing.innerText || timing.textContent;
	if (((minutes + 60 * heures) % valueTiming) == 0
			&& minutes > 0 && secondes == 0) {
		valider();
	}
}

function startChrono() {
	if (etatchronoProf == 0) {
		etatchronoProf = 1;
		chronoProf();
	}
}

function pause() {
	etatchronoProf = 0;
}

function maj() {
	var nbPersonne = document.getElementById("nbPersonne");
	nbPersonne.textContent = nbPersonnes;
	if(nbPersonnes != 0){
		var canvas = document.getElementById('affichageProf_canvas');
		if (!canvas) {
			alert("Impossible de récupérer le canvas");
			return;
		}
		var context = canvas.getContext('2d');
		if (!context) {
			alert("Impossible de récupérer le context du canvas");
			return;
		}
		initialisationContext(context);
		miseALEchelleCourbe(context);
		modificationDonneesPage(context);
		ajoutDerniereMoyenne(context);
		context.stroke();
		context.closePath();
	}
	
}

function ajoutDerniereMoyenne(context){
	var newX = abscissePointsDejaTrace[numeroPoint] + 10;
	var newY = 400 - ((moyenne - 1) * 100);
	context.moveTo(abscissePointsDejaTrace[numeroPoint],
			ordonneePointsDejaTrace[numeroPoint]);
	context.lineTo(newX, newY);
	numeroPoint += 1;
	abscissePointsDejaTrace.push(newX);
	ordonneePointsDejaTrace.push(newY);
}

function miseALEchelleCourbe(context){
	if (numeroPoint == 0) {
		ordonneePointsDejaTrace.push(400 - ((moyenne - 1) * 100));
	}
	if (numeroPoint % 10 == 0) {
		context.clearRect(0, 0, 700, 400);
		context.beginPath();
		for (var i = 1; i <= numeroPoint; i++) {
			if (abscissePointsDejaTrace[i] - 2 > abscissePointsDejaTrace[i - 1]) {
				abscissePointsDejaTrace[i] -= 2;
			}
			context.moveTo(abscissePointsDejaTrace[i - 1],
					ordonneePointsDejaTrace[i - 1]);
			context.lineTo(abscissePointsDejaTrace[i],
					ordonneePointsDejaTrace[i]);
		}
	}
}

function initialisationContext(context){
	context.clearRect(1, 2, 700, 400);
	context.clearRect(0, 0, 700, 400);
	context.lineWidth = 3;
	context.lineJoin = "round";
	context.lineCap = "round";
	context.strokeStyle = "black";
}

function modificationDonneesPage(context){
		var avisMoyen = document.getElementById("avisMoyen");
		avisMoyen.textContent = moyenne ;
		var src = document.getElementById("humeurMajoritaire").src;
		var structureSrc = src.split("/");
		src="";
		for(var i = 0 ; i+1 < structureSrc.length ; i++){
			src += structureSrc[i] + "/";
		}	
		switch (humeur) {
		case "colere":
			src += "humeur_colere.png";
			break;
		case "content":
			src += "humeur_content.png";
			break;
		case "triste":
			src += "humeur_triste.png";
			break;
		case "dort":
			src += "humeur_dort.png";
			break;
		case "rigole":
			src += "humeur_rigole.png";
			break;
		case "blaze":
			src += "humeur_blaze.png";
		}
		document.getElementById("humeurMajoritaire").src = src;
}

function valider() {
	if (window.XMLHttpRequest) {
		requete = new XMLHttpRequest();
		requete.open("GET", "professeur", true);
		requete.onreadystatechange = majIHM;
		requete.send(null);
	} else if (window.ActiveXObject) {
		requete = new ActiveXObject("Microsoft.XMLHTTP");
		if (requete) {
			requete.open("GET", "professeur", true);
			requete.onreadystatechange = majIHM;
			requete.send();
		}
	} else {
		alert("Le navigateur ne supporte pas la technologie Ajax");
	}
}

function majIHM() {
	if (requete.readyState == 4) {
		if (requete.status == 200) {
			// exploitation des données de la réponse
			var reponse = requete.responseText.split("&");
			moyenne = reponse[0];
			nbPersonnes = reponse[1];
			humeur = reponse[2];
			
			maj();
		} else {
			alert('Une erreur est survenue lors de la mise à jour de la page.'
					+ '\n\nCode retour = ' + requete.statusText);
		}
	}
}