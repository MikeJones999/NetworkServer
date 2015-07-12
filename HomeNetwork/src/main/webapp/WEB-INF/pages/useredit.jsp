<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<title>Edit User details page</title>
</head>
<body>

	<div class="thumbnail">
					<!--  <img src="<c:url value="/resources/images/avatar.png" />" alt="" /> -->

					<img src="<c:url value="/resources/images/avatar.png" />"
						alt="image" style="width: 5%" />
					<!-- 	<img src="${pageContext.servletContext.contextPath}/resources/images/avatar.png"/> -->
				</div>

	<section>



		<div class="jumbotron">
			<div class="container">

				<h1>Edit user details</h1>


				<h3>Edit the details for user: ${user.userName}</h3>
				<form method="POST" action="<c:url value="/userpage" />">
					<button type="submit">User home page</button>
				</form>


			</div>
		</div>


	</section>

	<section>

		<div
			style="text-align: center; padding: 25px; border: 2px solid green; width: 350px;">

			<form:form name="input" method="post" modelAttribute="user"
				action="/HomeNetwork/userpage/${user.userName}">

				<!-- <form:input type="hidden" path="userName" /> -->
				<table>
					<tr>
						<td><label>UserName:</label></td>
						<td><form:input type="text" path="userName" readonly="true" /></td>
					</tr>

					<tr>
						<td><label>User Role:</label>
						<td><form:input type="text" path="userRole" readonly="true" /></td>
					</tr>

					<tr>
						<td><label>Password:</label></td>
						<td><form:input type="text" path="password" /></td>
					</tr>


					<tr>
						<td> </td>
						<p></p>
						<td>
							<!-- <div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
								<input type="submit" id="btnAdd" class="btn btn-primary"
										value="Add Changes" />
							</div>
						--> <input class="btn btn-lg btn-success btn-block" type="submit"
							value="Apply Changes">
					</tr>
					</td>
					<!-- <td><input type="submit" value="Add changes" /></td> -->




					<tr>
						<td colspan="2" style="color: red">${useramended}</td>
					</tr>

				</table>


			</form:form>
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