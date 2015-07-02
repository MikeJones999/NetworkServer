<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Delete User Page</title>
</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Delete Admin User Page</h1>
			<p>Unable to confirm the deletion</p>

			<form action="<c:url value="/adminRedirect" />">
				<button type="submit">Admin Home Page</button>
			</form>
			<p> 
			</p>
			<form action="<c:url value="/adminpage/allusers" />">
				<button type="submit">Back (Show all Users)</button>
			</form>

		</div>
	</div>
	</section>

	<section>

	<h3>${message}</h3>

	</section>

</body>
</html>