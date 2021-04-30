<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="entity.Course"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%if(session.getAttribute("loggedIn") == null || !(Boolean)session.getAttribute("loggedIn")){
        response.sendRedirect("./index.jsp");
        return;
    }
    
%>
<html lang="it"> 
	<head>
	<meta charset="utf-8"> 
    	<meta name = "author" content = "Maurizio Pulizzi">
    	<meta name = "keywords" content = "palestra">
   	 	<link rel="shortcut icon" type="image/x-icon" href="./css/img/icon.ico" />
		<link rel="stylesheet" href="./css/homePrenotazioni.css" type="text/css" media="screen">
		<script  src="./js/handlerTastiPrenotazione.js"></script>
                <title>EF - Prenotazioni</title>
	</head>
	<body>
	<%@ include file="./headerPanel.jsp" %>
	<div id="menuBar">
            <input type="button" value=" " id="previous" onclick="location.href='./date?action=previous';">
            <input type="text" value="${coursesDate}" id="giorno" readonly>
            <input type="button" value=" " id="next" onclick="location.href='./date?action=next';">
	</div>
	<table id="tabellaPrenotazioni">
            <% List<Integer> booked = (List<Integer>)request.getServletContext().getAttribute("reservations");
            %>
            <%for(Course course: (List<Course>)request.getServletContext().getAttribute("courses")){ %>
                 <tr>
                     <th class="colOrario"> 
                         <% out.print(course.getStartTime().toString().substring(11, 16)+" - "+course.getEndTime().toString().substring(11, 16));%>
                     </th>
                     <td>
                         <%
                             String class_ = "possibilePrenotare";
                             if(booked.contains(course.getIdcourse())){
                                 class_ = "prenotato";
                             } else if(course.getRemainingPeopleNumber()<= 0){
                                 class_ = "esaurito";
                             }
                         %>
                         <div id="<%=course.getIdcourse()%>" class="<%=class_%>">
                            <div class="nomeCorso">
                                <%=course.getName()%>
                            </div>
                            <div class="postiDisponibili">
                                Posti rimanenti: <%=course.getRemainingPeopleNumber()%>
                            </div>
                            <%if(class_.compareTo("possibilePrenotare")==0){ %>
                                <input type="button" class="invioPren" value="Prenota" onclick="HandlerTastiPrenotazione.eventoPrenotazione(this.parentNode.id)">
                            <%}
                            else if(class_.compareTo("prenotato")==0){%>
                            <input type="button" class="invioPren" value="Annulla prenotazione" onclick="HandlerTastiPrenotazione.eventoPrenotazione(this.parentNode.id)">
                            <%}%>
                         </div>
                     </td>
                 </tr>
            <% }  %>
            
	</table>
	<div id="avvisi">
	</div>
</html>
