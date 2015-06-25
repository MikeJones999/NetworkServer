<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<title>Login Page</title>
</head>
<body>



		<h1>${response}</h1>

		<h2>Enter your details to log in</h2>


		<div style="text-align: center; padding: 20px; border: 1px solid green; width: 200px;">
		
			<form method="post"	action="<c:url value='j_spring_security_check' />">

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

	<form action="<c:url value="/" />">
		<button type="submit">Return to Home Page</button>
	</form>

</body>
</html>
