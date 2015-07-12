<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<title>Upload Multiple Files Example</title>

</head>
<body>


	<section>
		<div align="left">
			<div class="jumbotron">
				<div class="container">
					<h1>${message}</h1>

					<form method="POST" action="<c:url value="/userpage" />">
						<button type="submit">User home page</button>
					</form>


				</div>

			</div>
		</div>
	</section>

	<br>
	<br>


		<c:forEach items="${filesUploaded}" var="file">
			- ${file} <br>
		</c:forEach>


	<br />
	<br />

<h1>${message1}</h1>
<h3>${message2}</h3>
	<ol>
		<c:forEach items="${filesNotUploaded}" var="file">
			- ${file} <br>
		</c:forEach>
	</ol>



</body>
</html>