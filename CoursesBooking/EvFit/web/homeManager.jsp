<%@page import="entity.Course"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%if(session.getAttribute("manager") == null || !(Boolean)session.getAttribute("manager")){
        response.sendRedirect("./index.jsp");
        return;
}
    
%>
<html lang="it">
<head>
        <meta charset="utf-8"> 
	<meta name = "author" content = "Maurizio">
	<link rel="shortcut icon" type="image/x-icon" href="./css/img/icon.ico" />
	<link rel="stylesheet" href="./css/calendarioCorsi.css" type="text/css" media="screen">
	<link rel="stylesheet" href="./css/pannelloData.css" type="text/css" media="screen">
        <script src="./js/gestoreTastiModifica.js"></script>
	<title>EF Amministrazione - corsi</title>
</head>
<body onload="coursehandler_admin.deleteCourse">
    <%@ include file="./headerPanel.jsp" %>
    <div>
        <div id="menuBar">
            <input type="button" value=" " id="previous" onclick="location.href='./date?action=previous';">
            <input type="text" value="${coursesDate}" id="giorno" readonly>
            <input type="button" value=" " id="next" onclick="location.href='./date?action=next';">
	</div>
        <div id="wrapperTable">
            <table id="tabellaPrenotazioni">
                    <%for(Course course: (List<Course>)request.getServletContext().getAttribute("courses")){ %>
                        <tr>
                            <th class="colOrario"> 
                                <% out.print(course.getStartTime().toString().substring(11, 16)+" - "+course.getEndTime().toString().substring(11, 16));%>
                            </th>
                            <td>
                               
                                <div id="<%=course.getIdcourse()%>" class="corso">
                                
                                
                                   <div class="nomeCorso">
                                       <%=course.getName()%>
                                   </div>
                                   <div class="postiDisponibili">
                                       Posti rimanenti: <%=course.getRemainingPeopleNumber()%>
                                   </div>
                                   <input type="button" value="Elimina" onclick="gestoreTastiModifica.deleteCourse(this.parentNode.id)">

                                </div>
                                
                            </td>
                        </tr>
                        
                   <% }  %>
            </table>
	</div>
        <fieldset id="inserisciCorso">
		    <legend>Inserisci un corso in calendario :</legend>
			<label>Nome corso:<br>
				<input id="corsoDaInserire" class="datiCorso" name="Nome corso:" required>
                                <br>
			
			</label>
			
			<label>Ora inizio:<br>
				<input id="OraInizio" name="ora inizio" class="datiCorso" type="text" placeholder="hh:mm"
					pattern="([01]?[0-9]|2[0-3]):[0-5][0-9]" required><br>
			</label>
			<label>OraFine:<br>
				<input id="OraFine" name="ora fine" class="datiCorso" type="text" placeholder="hh:mm"
					pattern="([01]?[0-9]|2[0-3]):[0-5][0-9]" required><br>
			</label>
			<label>Data:<br>
				<input id="DataInizio" name="data inizio" class="datiCorso" 
					type="text" placeholder="YYYY-MM-DD" required
					pattern="((20|21)\d\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"><br>
			</label>
                        <label>Numero posti:<br>
				<input id="numPosti" name="numero posti" class="datiCorso" 
					type="text" placeholder="0" required><br>
			</label>
			<input id="bottoneAggiungi" value="Aggiungi" type="button"
				onClick="gestoreTastiModifica.addCourse()">
	</fieldset>
    </div>
    
</body>
</html>