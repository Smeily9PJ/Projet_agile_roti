//http://dmouronval.developpez.com/tutoriels/css/animation-css3-horloge/
function horlogeFonction(){
    var prefixer = new PrefixFinder(),
        tt = new Date(),
        jsTransform = prefixer.getPrefixedProp('transform').js,
        cssTransform = prefixer.getPrefixedProp('transform').css,
        jsAnim = prefixer.getPrefixedProp('animation').js,
        $sep = document.createElement('div'),
        $sepactu,
        $horloge = document.getElementById('horloge'),
        secondeCSS = document.getElementById('seconde').style,
        minuteCSS = document.getElementById('minute').style,
        heureCSS = document.getElementById('heure').style;
    var styleElement = document.createElement('style');
    document.getElementsByTagName('script')[0].parentNode.insertBefore(styleElement, document.getElementsByTagName('script')[0]);
    try{
        styleElement.sheet.insertRule('@keyframes tour{from{' + cssTransform + ': rotate(0deg);}to{' + cssTransform + ': rotate(360deg)}}', 0);
    }
    catch(e){
        try{
            styleElement.sheet.insertRule('@' + prefixer.prefixe.css + 'keyframes tour{from{' + cssTransform + ': rotate(0deg);}to{' + cssTransform + ': rotate(360deg)}}', 0);
        }
        catch(e){
            $horloge.id = '';
            $horloge.className = '';
            $horloge.style.border = '1px solid #25465B';
            $horloge.style.margin = '10px 40px';
            $horloge.style.padding = '10px 10px 10px 50px';
            $horloge.style.background = 'url("http://www.developpez.com/template/kit/kiterror.png") no-repeat scroll 10px center #FFBCC5';
            $horloge.innerHTML = 'Votre navigateur est trop ancien pour supporter cet exemple.<br />Vous devriez le mettre à jour pour pouvoir profiter pleinement des nouveautés offertes par HTML5 / CSS 3 / JavaScript.';
            return false;
        }
    }
    $sep.className = 'sep';
    secondeCSS[jsAnim] = 'tour 60s -' + (tt.getSeconds()) + 's steps(60, end) infinite';
    secondeCSS[jsTransform + 'Origin'] = '50% 175px';
    minuteCSS[jsAnim] = 'tour 3600s -' + (tt.getMinutes()*60 + tt.getSeconds()) + 's steps(60, end) infinite';
    minuteCSS[jsTransform + 'Origin'] = '50% 145px';
    heureCSS[jsAnim] = 'tour 43200s -' + ((tt.getHours()%12)*3600 + tt.getMinutes()*60 + tt.getSeconds()) + 's linear infinite';
    heureCSS[jsTransform + 'Origin'] = '50% 100px';
    for(var i = 0; i < 12; i++){
        $sepactu = $sep.cloneNode(true);
        $sepactu.style[jsTransform] = 'rotate(' + (i*30) + 'deg) translateY(-190px)';
        $horloge.appendChild($sepactu);
    }
}