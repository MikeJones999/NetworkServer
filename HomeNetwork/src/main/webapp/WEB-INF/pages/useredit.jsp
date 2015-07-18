<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">



<link href="<c:url value="/resources/myLayout/css/873366.css" />"
	rel="stylesheet" type="text/css" />
<style>
h1, h2, h3, h5, h6 {
	text-align: center;
}

h4 {
	text-align: right;
}
</style>
<title>Edit User details page</title>
</head>

<body>
	<div id="wrapper">
		<div id="headerwrap">
			<div id="header">

				<TABLE BORDER="0">
					<TR>

						<td>
							<form action="<c:url value="/userpage" />">
								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="User home page" />
									</div>
								</div>
							</form>
						</td>

<!-- 
						<td width=650><img
							src="<c:url value="/resources/images/avatar.png" />" alt="image"
							style="width: 2%" />
						</td>

-->
						<td width=850>
						<td colspan="6">
							<form action="<c:url value="/j_spring_security_logout" />">

								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="Logout" />
									</div>
								</div>

							</form>
						</td>

					</TR>
				</TABLE>
				<!--  only two div here for some reason -->
			</div>
		</div>




		<div id="navigationwrap">
			<div id="navigation">
				<h3>Edit the details for user: ${user.userName}</h3>
			</div>
		</div>

		<div id="leftcolumnwrap">
			<div id="leftcolumn">

				<h5>Please change your password in the box to the right</h5>
				<h5>You cannot change your role or your Username!!</h5>
			</div>
		</div>


		<div id="contentwrap">
			<div id="content">


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

								<td><input class="btn btn-lg btn-success btn-block"
									type="submit" value="Apply Changes"></td>
							</tr>


							<tr>
								<td colspan="2" style="color: red">${useramended}</td>
							</tr>

						</table>


					</form:form>
				</div>

			</div>
		</div>


		<div id="footerwrap">
			<div id="footer">

				<TABLE BORDER="0">
					<TR>
						<td width=10>
						<td><h4>
								Logged in as:
								<%=request.getRemoteUser()%></h4></td>
						<td width=810>
							<h4>Role: ${user.userRole}</h4>
						</td>
					</TR>
					</div>
					</div>
</body>
</html>
