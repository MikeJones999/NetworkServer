<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Administration Access Area</title>
</head>
<body>

 <center>

  <h2>Welcome to the Admin Access Area</h2>


 	<form method="POST" action="<c:url value="adminpage/addUsers" />">
    <button type="submit">Add Users to Database</button>
	</form>

 	<form method="POST" action="<c:url value="adminpage/allusers" />">
    <button type="submit">Show all Users of Database</button>
	</form>




  <c:url value="/j_spring_security_logout" var="logoutUrl" />
  <h3><a href="${logoutUrl}">Logout</a></h3>

 </center>
</body>
</html>