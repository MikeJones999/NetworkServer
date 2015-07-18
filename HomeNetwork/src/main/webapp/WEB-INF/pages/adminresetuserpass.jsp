<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Password rest for User</title>
<style type="text/css">
h1, h3, h4, h5 {
	text-align: center;
}

</style>


</head>

<body>

<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Password reset for User: ${userName}</h1>
				
				<form action="<c:url value="/adminRedirect" />">
					<button type="submit">Admin Home Page</button>
				</form>
				<form action="<c:url value="/adminpage/allusers" />">
					<button type="submit">Show all users</button>
				</form>
			</div>
		</div>
	</section>


<h3>${passwordResetConfirmation}</h3>
<p> </p>
<h3>${passwordWarning}</h3>

</body>
</html>