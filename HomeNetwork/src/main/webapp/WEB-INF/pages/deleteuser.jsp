<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete User Page</title>
</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Delete User Page</h1>
			<p>Confirm delete to remove user</p>
			<h1>Are you sure you want to delete user: ${message} ?</h1>

		</div>
	</div>
	</section>

<section>
	<form method="POST" action="<c:url value="/adminpage/allusers" />">
		<button type="submit">Confirm Deletion of User</button>
	</form>
	<form action="<c:url value="/adminRedirect" />">
		<button type="submit">Admin Home Page</button>
	</form>
</section>

</body>
</html>