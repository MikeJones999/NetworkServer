<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Image viewer file</title>


</head>
<body>
<header>

				<p></p>
				<form
					action="<c:url value="/userpage/${user.userName}/${folderType}/handlefile/${file}" />">
					<div class="form-group">
						<div class="col-lg-offset-0 col-lg-10">
							<input type="submit" id="btnAdd" class="btn btn-primary"
								value=" Back to File  " />
						</div>
					</div>
				</form>
</header>				
<content>
<p></p>
<img src="data:image/jpeg;base64,${image}" alt="..." width="640" height="480">`
</content>



</body>
</html>