window.onbeforeunload = confirmExit;
window.onclick = function(e) {
	e = e || window.event;//pour que tous les navigateurs choppent l'event
	e = (e.target || e.srcElement).tagName.toLowerCase();//pour que ça marche chez IE et chez les autres et que le tagName soit en minuscules
	if(e == "button") {//si c'est un lien
		window.onbeforeunload = function() {};//on fait en sorte que le onunload soit null.
	}
}
function confirmExit()
{ 
	return 'Etes-vous sûr(e) de vouloir quitter ?';
}