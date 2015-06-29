<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Unknown User</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>User ${warnimgMessage} is unknown</h1>
				<p></p>
				
			</div>
		</div>
	</section>
	
	<section>
		<form action="<c:url value="/adminRedirect" />">
			<button type="submit">Admin Home Page</button>
		</form>

		<center>
			<c:url value="/j_spring_security_logout" var="logoutUrl" />
			<h3>
				<a href="${logoutUrl}">Logout</a>
			</h3>
		</center>
	</section>
</body>
</html>