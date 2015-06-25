<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>User Access Area</title>
</head>
<body>

 <center>

  <h2>Welcome to the User Access Area. You have now logged in</h2>

  <c:url value="/j_spring_security_logout" var="logoutUrl" />
  <h3><a href="${logoutUrl}">Logout</a></h3>

 </center>
</body>
</html>