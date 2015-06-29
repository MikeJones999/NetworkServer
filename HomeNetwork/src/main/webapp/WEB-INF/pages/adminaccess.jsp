<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Administration Access Area</title>
</head>
<body>




	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Welcome to the Administrator Area</h1>
				<p>All Users from the table 'users'</p>
		
				<form method="POST" action="<c:url value="adminpage/addUsers" />">
					<button type="submit">Add Users to Database</button>
				</form>

				<form method="POST" action="<c:url value="adminpage/allusers" />">
					<button type="submit">Show all Users of Database</button>
				</form>
			</div>
		</div>
	</section>





<center>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<h3>
		<a href="${logoutUrl}">Logout</a>
	</h3>
</center>

</body>
</html>