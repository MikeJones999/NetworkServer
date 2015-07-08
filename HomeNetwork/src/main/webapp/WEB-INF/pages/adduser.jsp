
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Add new user Page</title>
</head>
<body>


	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Administration Section</h1>
				<p>Add New User</p>

				<form action="<c:url value="/adminRedirect" />">
					<button type="submit">Admin Home Page</button>
				</form>

			</div>
		</div>
	</section>

	<section>
	
		<div
		style="text-align: center; padding: 25px; border: 2px solid green; width: 350px;">
	
		<form:form method="post" action="usersAdded" commandName="user">
			

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
					<td>Select Role :</td>
					<td><form:select path="userRole">
							<!-- <form:option value="NONE" label="--Select Role--" /> -->
							<form:options items="${roleOptions}" />
						</form:select>
					</td>
				</tr>


				<tr>
					<td> </td>
					<td><input type="submit" value="Add New User" /></td>

				</tr>


				<tr>
					<td colspan="2" style="color: red">${useradded}</td>
				</tr>

			</table>

		</form:form>

	</div>
	</section>

	<section></section>


	<center>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<h3>
			<a href="${logoutUrl}">Logout</a>
		</h3>

	</center>



</body>
</html>