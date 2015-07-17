<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Welcome page - Home Server</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
			
				<P>${response}</P>
				<h1>${message1}</h1>
				<h1>${message2}</h1>
				<h3>${message3}</h3>
				


				<form action="<c:url value="/userpage" />">
					<button type="submit">User Login Page</button>
				</form>

				<form action="<c:url value="/adminpage" />">
					<button type="submit">Administrator Login Page</button>
				</form>
			</div>
		</div>
	</section>



</body>
</html>