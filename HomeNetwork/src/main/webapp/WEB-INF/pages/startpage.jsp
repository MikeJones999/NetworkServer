<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<title>Welcome page - see by all accessing the server</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>${message1}</h1>
				<p>${message2}</p>
			</div>
		</div>
	</section>
	
	<form action="<c:url value="/userpage" />">
    <button type="submit">User Login Page</button>
	</form>
	
	<form action="<c:url value="/adminpage" />">
    <button type="submit">Administrator Login Page</button>
	</form>
	
</body>
</html>