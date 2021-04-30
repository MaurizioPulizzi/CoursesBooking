function gestoreTastiModifica(){}

gestoreTastiModifica.NOME_UT;
gestoreTastiModifica.URL_REQUEST = "./course";

gestoreTastiModifica.deleteCourse = 
        function(idcorso){
            var r = window.confirm("Do you want to delete this course from the calendar?");
            
            if (r == true) {
		var url = gestoreTastiModifica.URL_REQUEST + "?courseId=" + idcorso + "&action=delete";
                window.location.href = url;
            } 
}

gestoreTastiModifica.addCourse =
        function(){
            var fields=document.getElementsByClassName("datiCorso");
            var dati=new Array();
            for(var i=0; i<fields.length; i++){
                if(fields[i].validity.valueMissing){
                    window.alert("compilare tutti i campi");
                    return false;
                }
                if(fields[i].validity.patternMismatch){
                    window.alert(fields[i].name + " non valida");
                    return false;
                }
                dati[i]=fields[i].value;
            }
            var queryString="corso="+dati[0] + "&oraInizio="+dati[1]
                    +"&oraFine="+dati[2] + "&data="+dati[3]+"&numPosti=" + dati[4];
            var url = gestoreTastiModifica.URL_REQUEST + "?action=add&"+queryString;
            window.location.href = url;
}

