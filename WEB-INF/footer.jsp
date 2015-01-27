<footer id="pied">
 <div id="infos_roti" onMouseOver="div.show('infos_txt_roti')" onMouseOut="div.hide('infos_txt_roti')">Qu'est-ce que c'est ?</div>
  <div id="infos_credit" onMouseOver="div.show('infos_txt_credit')" onMouseOut="div.hide('infos_txt_credit')">Credit</div>
  <div id="infos_txt_roti">
  Cette application Web sert à avoir un retour
  sur le temps investi lors d'une conférence en
  live. Elle permet d'adapter son discours à
  l'auditoire et son ressenti.
 </div>
  <div id="infos_txt_credit">
 	Licence Pro - Développement Qualité et Logiciel<br>
 	TIRADON Pierre . 
 	SIRE DE VILAR Arnaud . 
 	PELISSOU Julie<br>
 	PEDURAND Claire . 
 	BONIS Maxime
 </div>
</footer>

<script type="text/javascript">
	div = {
		show: function(elem) {
			document.getElementById(elem).style.visibility = 'visible';
		},
		hide: function(elem) {
			document.getElementById(elem).style.visibility = 'hidden';
		}
	}
</script>