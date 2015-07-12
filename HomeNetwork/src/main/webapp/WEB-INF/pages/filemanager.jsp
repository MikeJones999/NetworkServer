<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Manager Page</title>
</head>
<body>
	<div align="Right">
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<h3>
			<a href="${logoutUrl}">Logout</a>
		</h3>
	</div>
	
	<section>

	<div class="jumbotron">
		<div class="container">
			<h1>File Manager Page</h1>
			<h3>Access public/private folders from this page</h3>
			<p></p>
			
		
			<form method="POST" action="<c:url value="/userpage" />">
				<button type="submit">User home page</button>
			</form>
			<p></p>
			<form method="POST"
				action="<c:url value="/userpage/${user.userName}/public" />">
				<button type="submit">Public Folder</button>
			</form>
			<p></p>
			<form method="POST" action="<c:url value="/userpage" />">
				<button type="submit">Private Folder</button>
			</form>
		

		</div>

	</div>

	</section>





</body>
</html>