  function create(){
            document.getElementById('accueil_create').style.display = 'none';
            document.getElementById('accueil_ClickCreate').style.display = 'block';
            if(document.getElementById('accueil_join').style.display == 'none')
                document.getElementById('accueil_ClickJoin').style.display = 'none';
                document.getElementById('accueil_join').style.display = 'block';
        }
        function join(){
            document.getElementById('accueil_join').style.display = 'none';
            document.getElementById('accueil_ClickJoin').style.display = 'block';
            if(document.getElementById('accueil_create').style.display == 'none')
                document.getElementById('accueil_ClickCreate').style.display = 'none';
                document.getElementById('accueil_create').style.display = 'block';
        }