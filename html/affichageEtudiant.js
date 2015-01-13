var secondes=20
var minutes=0

function chrono(){
	var chrono = document.getElementById("chrono");
	var newChrono ;
	if (secondes == 0){
		if (minutes == 0){
			newChrono = "Maintenant !";
			document.getElementById("bouton").style.display = "block";
		}else{
			secondes=59; minutes--;
			newChrono = minutes +" minutes et "+ secondes +"secondes.";
		}
	}else{
		secondes--;
		newChrono = minutes +" minutes et "+ secondes +"secondes.";
	}
	chrono.textContent = newChrono;
	setTimeout('chrono()',1000) //la fonction est relancée
}