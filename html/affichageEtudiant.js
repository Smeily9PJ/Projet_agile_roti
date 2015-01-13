var secondes=0
var minutes=1

function chrono(){
	var chrono = document.getElementById("chrono");
	var newChrono ;
	if (secondes == 0){
		if (minutes == 0){
			newChrono = "maintenant";
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