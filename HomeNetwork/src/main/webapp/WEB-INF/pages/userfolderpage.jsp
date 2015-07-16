<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Private file Manager Page</title>
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
			<h1>${user.userName}'s ${folderType} Folder Page</h1>
			<h3>${warningPublicPageMessage}</h3>
	
			<p></p>
			<form method="POST" action="<c:url value="/userpage" />">
				<button type="submit">User home page</button>
			</form>
			<p></p>
			<form method="POST"
				action="<c:url value="/userpage/${user.userName}/filemanager" />">
				<button type="submit">File Manager</button>
			</form>
			<p></p>
			<form method="POST"
				action="<c:url value="/userpage/${user.userName}/${folderType}/upload" />">
				<button type="submit">Upload file(s)</button>
			</form>
			<!-- 
			<p></p>
			<form method="POST" action="<c:url value="/userpage" />">
				<button type="submit">View all Folders/Files</button>
			</form>
			-->

		</div>

	</div>

	</section>

	<section class="container">
	<div class="row">
		<h1>Files Found in ${user.userName} ${folderType} Folder</h1>
		<c:forEach items="${filesFound}" var="file">
			<div class="col-sm-3 col-md-2" style="padding-bottom: 5px">
				<div class="thumbnail">
					<div class="caption">
						<div style="text-align: center;">
							<div style="font-size: small;">${file}</div>
						</div>

						<form  
							action="<c:url value="/userpage/${user.userName}/${folderType}/download/${file}" />">
							<div style="text-align: center;">
								<button type="submit">Download file</button>
							</div>							
						</form>
						<form 
							action="<c:url value="/userpage/${user.userName}/${folderType}/delete/${file}" />">
							<div style="text-align: center;">
								<button type="submit">Delete file</button>
							</div>							
						</form>

						
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	</section>




</body>
</html>