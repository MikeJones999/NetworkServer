<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>User Profile Page</title>

</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>${user.userName}'s Profile page</h1>


			<form
				action="<c:url value="/userpage/${user.userName}/editprofile" />">
				<button type="submit">Edit User details</button>
			</form>

			<p></p>
			<form method="GET" action="<c:url value="/userpage/${user.userName}/filemanager" />">
				<button type="submit">File Manager page</button>
			</form>
			<p></p>

		</div>
	</div>
	</section>


	<section>
	<div>
		<h3>User: ${user.userName}</h3>
	</div>
	<div>
		<h3>Role: ${user.userRole}</h3>
	</div>

	<%=request.getRemoteUser()%> </section>

	<center>
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<h3>
			<a href="${logoutUrl}">Logout</a>
		</h3>

	</center>


</body>
</html>