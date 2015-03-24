var secondes = 0;
var minutes = 0;
var heures = 0;
var numeroPoint = 0;
var abscissePointsDejaTrace = [ 0 ];
var ordonneePointsDejaTrace = [];
var moyenne = 3;
var nbPersonnes = 0;

var etatChrono = 0; // 0 = arret, 1 = marche

function chrono() {
	if (etatChrono == 1) {
		var chrono = document.getElementById("affichageProf_chono");
		var newChrono;
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
		newChrono = heures + " : " + minutes + " : " + secondes;
		chrono.textContent = newChrono;
		var timing = document.getElementById("timingVote");
		var valueTiming = timing.innerText || timing.textContent;
		if (((minutes + 60 * heures) % valueTiming) == 0
				&& minutes > 0 && secondes == 0) {
			valider();
		}
		setTimeout('chrono()', 1000) // la fonction est relancée
	}
}

function start() {
	if (etatChrono == 0) {
		etatChrono = 1;
		chrono();
	}
}

function pause() {
	etatChrono = 0;
}

function tracerCourbe() {
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

	context.clearRect(1, 2, 700, 400);
	context.clearRect(0, 0, 700, 400);
	context.lineWidth = 3;
	context.lineJoin = "round";
	context.lineCap = "round";
	context.strokeStyle = "black";
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

	var nbPersonne = document.getElementById("nbPersonne");
	nbPersonne.textContent = nbPersonnes;

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
		src += "humeur_colere.gif";
		break;
	case "content":
		src += "humeur_content.gif";
		break;
	case "triste":
		src += "humeur_triste.gif";
		break;
	case "dort":
		src += "humeur_dort.gif";
		break;
	case "rigole":
		src += "humeur_rigole.gif";
		break;
	case "blaze":
		src += "humeur_blaze.gif";
	}
	document.getElementById("humeurMajoritaire").src = src;
	var newX = abscissePointsDejaTrace[numeroPoint] + 10;
	var newY = 400 - ((moyenne - 1) * 100);
	context.moveTo(abscissePointsDejaTrace[numeroPoint],
			ordonneePointsDejaTrace[numeroPoint]);
	context.lineTo(newX, newY);
	numeroPoint += 1;
	abscissePointsDejaTrace.push(newX);
	ordonneePointsDejaTrace.push(newY);
	context.stroke();
	context.closePath();
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
			tracerCourbe();
		} else {
			alert('Une erreur est survenue lors de la mise à jour de la page.'
					+ '\n\nCode retour = ' + requete.statusText);
		}
	}
}