<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>All users</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Displaying All users</h1>
				<p>All Users from the table 'users'</p>
				<form action="<c:url value="/adminRedirect" />">
					<button type="submit">Admin Home Page</button>
				</form>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
		
		<td colspan="2" style="color: red">${userRemoved}</td>
		
			<h1>All Administrators</h1>

			<c:forEach items="${allAdmins}" var="user">
				<div class="col-sm-4 col-md-3" style="padding-bottom: 5px">
					<div class="thumbnail">
						<div class="caption">
							<h3>${user.userName}</h3>
							<!-- <p>${user.password}</p> -->
							<p>${user.userRole}</p>
							<!-- 
							<form method="post" action="/adminpage/deleteUser/${user.userName}">
							<td><input type="submit" value="Delete User" /></td>
							</form>
							-->
							<form
								action="<c:url value="/adminpage/deleteUser/${user.userName}" />">
								<button type="submit">Delete User</button>
							</form>


						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</section>

	<section class="container">
		<div class="row">
			<h1>All Users</h1>
			<c:forEach items="${allusers}" var="user">
				<div class="col-sm-4 col-md-3" style="padding-bottom: 5px">
					<div class="thumbnail">
						<div class="caption">
							<h3>${user.userName}</h3>
							<!-- <p>${user.password}</p> -->
							<p>${user.userRole}</p>
							<!-- 
							<form method="post" action="/adminpage/deleteUser/${user.userName}">
							<td><input type="submit" value="Delete User" /></td>
							</form>
							-->
							<form
								action="<c:url value="/adminpage/deleteUser/${user.userName}" />">
								<button type="submit">Delete User</button>
							</form>


						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>

	<section>


		<center>
			<c:url value="/j_spring_security_logout" var="logoutUrl" />
			<h3>
				<a href="${logoutUrl}">Logout</a>
			</h3>
		</center>
	</section>
</body>
</html>