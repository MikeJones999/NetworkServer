<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">


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

		<div class="login">


			<form method="post"  action="<c:url value='j_spring_security_check' />">

				<input type="text" class="login-input" placeholder="username" name="username" /> 
					<input type="password" class="login-input" 	placeholder="password" name="password" />
					 <input type="submit"  class="login-submit" value="Login" />

			</form>

		</div>

	</section>

</body>
</html>
