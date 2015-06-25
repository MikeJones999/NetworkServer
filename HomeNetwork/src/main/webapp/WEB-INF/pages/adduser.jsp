
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>Add new user Page</title>
</head>
<body>


	<h2>Administration Section</h2>
	<h3>Add New User</h3>

	<form method="post" action="usersAdded">

		<table>
			<tr>
				<td colspan="2" style="color: red">${message}</td>
			</tr>


			<tr>
				<td>User Name:</td>
				<td><input type="userName" name="userName" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td> </td>
				<td><input type="submit" value="Add New User" /></td>

			</tr>
		</table>

	</form>


	<form action="<c:url value="/adminRedirect" />">
		<button type="submit">Admin Home Page</button>
	</form>



	<center>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<h3>
			<a href="${logoutUrl}">Logout</a>
		</h3>

	</center>



</body>
</html>