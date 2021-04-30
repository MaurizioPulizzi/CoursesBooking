<!DOCTYPE html>
<%if(session.getAttribute("loggedIn") != null && (Boolean)session.getAttribute("loggedIn")){
        response.sendRedirect("./homePrenotazioni.jsp");
        return;
    }
    
%>
<html lang="it">
	<head>
	<meta charset="utf-8"> 
    	<meta name = "author" content = "Maurizio">
    	<meta name = "keywords" content = "palestra">
   	 	<link rel="shortcut icon" type="image/x-icon" href="./css/img/icon.ico" />
		<link rel="stylesheet" href="./css/login.css" type="text/css" media="screen">
		<script src="./js/effects.js"></script>
		<script src="./js/evfit.js"></script>		
		<title>Evolution Fitness</title>
	</head>
	<body onLoad="begin()">
		
			<img id="logo" src="./img/logo-3.png" alt="Logo palestra">
		
		<section id="sign_in_content">
		<h2 id="sign_in_content_header">
			Accesso all'area clienti
		</h2>
		<div id="login_form">
			<form name="login" action = "./login" method="POST">
				<div>
					<label>Username</label>
					<input type="text" placeholder="Username" name="username" required
						pattern="[A-Za-z1-9\.\-_]+" autofocus>
				</div>
				<div>
					<label>Password</label>
					<input type="password" placeholder="Password" name="password" required>
				</div>	
				<input type="submit" value="Enter">
                                <%
                                if(session.getAttribute("loggedIn") != null && !(Boolean)session.getAttribute("loggedIn")) {
                                %>
                                <div class="sign_in_error">
                                <span>Login failed. Please try again.</span>
                                </div>

                                <%
                                    session.removeAttribute("loggedIn");
                                }
                                %>
				
			</form>
		</div>                
		</section>
		
	</body>
</html>
