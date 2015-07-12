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

	<section>

	<div class="jumbotron">
		<div class="container">
			<h1>${user.userName}'s Public Folder Page</h1>
			<h3>This page and its contents are not secured and open for
				sharing/downloading</h3>
			<p></p>
			<form method="POST" action="<c:url value="/userpage" />">
				<button type="submit">User home page</button>
			</form>
			<p></p>
			<form method="POST"
				action="<c:url value="/userpage/${user.userName}/public/upload" />">
				<button type="submit">Upload file</button>
			</form>
			<p></p>
			<form method="POST" action="<c:url value="/userpage" />">
				<button type="submit">View all Folders/Files</button>
			</form>

		</div>

	</div>

	</section>





</body>
</html>