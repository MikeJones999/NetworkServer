<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Edit User details page</title>
</head>
<body>




	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Edit user details</h1>
						
				<form method="POST" action="<c:url value="/userpage" />">
					<button type="submit">User home page</button>
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