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
h1, h2, h3, h6 {
	text-align: center;
}

#btnAdd {document .getElementById("myBtn").style.bottom = "200px";
	
}

p {
	text-align: center;
}

.floated {
	float: left;
	margin-right: 5px;
}
</style>
<title>Welcome page - Home Server</title>
</head>

<body>
	<div id="wrapper">
		<div id="headerwrap">
			<div id="header">
				<TABLE BORDER="0">
					<TR>
						<td><form action="<c:url value="/" />">
								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="Return to Home Page" />
									</div>
								</div>
							</form></td>
					</TR>
				</TABLE>
			</div>
		</div>

		<div id="navigationwrap">
			<div id="navigation">
				<h2>${response}</h2>
				<h1>Login section of Home Network Server</h1>
			</div>
		</div>

		<div id="leftcolumnwrap">
			<div id="leftcolumn">

				<h6>Enter your details to log in</h6>
			</div>
		</div>


		<div id="contentwrap">
			<div id="content">
				<section>
					<div class="login">
						<form method="post"
							action="<c:url value='j_spring_security_check' />">

							<input type="text" class="login-input" placeholder="username"
								name="username" /> <input type="password" class="login-input"
								placeholder="password" name="password" /> <input type="submit"
								class="login-submit" value="Login" />

						</form>
					</div>
				</section>

			</div>
		</div>


		<div id="footerwrap">
			<div id="footer">
				<p>This is the Footer</p>
			</div>
		</div>
	</div>
</body>
</html>