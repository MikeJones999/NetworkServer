<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Login Page</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>${response}</h1>

				<h2>Login section of Home Network Server</h2>
			

				<form action="<c:url value="/" />">
					<button type="submit">Return to Home Page</button>
				</form>

			</div>
		</div>
	</section>



	<section>
	<h2>Enter your details to log in</h2>
	<div
		style="text-align: center; padding: 25px; border: 2px solid green; width: 350px;">

		<form method="post" action="<c:url value='j_spring_security_check' />">

			<table>
				<tr>
					<td>User Name:</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td> </td>
					<td><input type="submit" value="Login" /></td>

				</tr>
			</table>
		</form>
	</div>

	</section>

</body>
</html>
