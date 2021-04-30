/*Questo file si occupa di gestire gli eventi di prenotazione e
annullamento prenotazione dei singoli corsi da parte dell'utente*/
function HandlerTastiPrenotazione(){}

HandlerTastiPrenotazione.DEFAUL_METHOD = "GET";
HandlerTastiPrenotazione.URL_REQUEST = "./reservation";

HandlerTastiPrenotazione.PRENOTA="1";
HandlerTastiPrenotazione.ANNULLA="-1";


HandlerTastiPrenotazione.eventoPrenotazione=
	function(idCorso){
		var azione = HandlerTastiPrenotazione.setAction(idCorso);
		if(azione===null) return;
		  
		var queryString = "?courseId=" + idCorso + "&action=" + azione;
		var url = HandlerTastiPrenotazione.URL_REQUEST + queryString;
		window.location.href = url;
        }
	

HandlerTastiPrenotazione.setAction=
		function(idCorso){
			var elem=document.getElementById(idCorso);
			var stato=elem.getAttribute("class");
			if(stato=="prenotato") return -1;
			if(stato=="possibilePrenotare") return 1;
			return null;
		}

