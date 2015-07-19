<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<link href="<c:url value="/resources/myLayout/css/873366.css" />"
	rel="stylesheet" type="text/css" />
<style>
h1, h2, h3, h5 {
	text-align: center;
}
h6
{
	text-align: left
}

h4 {
	text-align: right;
	p
	{
	text-align
	:
	center;
}


</style>
<title>${folderType}ManagerPage</title>
</head>

<body>
	<div id="wrapper">
		<div id="headerwrap">
			<div id="header">

				<TABLE BORDER="0">
					<TR>
						<td><form action="<c:url value="/userpage/${user.userName}" />">
								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="User home page" />
									</div>
								</div>
							</form></td>
						<td><form
								action="<c:url value="/userpage/${user.userName}/filemanager" />">
								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="File Manager" />
									</div>
								</div>
							</form></td>
						<td><form
								action="<c:url value="/userpage/${user.userName}/${folderType}/upload" />">
								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="Upload ${folderType} file(s)" />
									</div>
								</div>
							</form></td>

						<td>
						<td width=600>
						<td colsp an="6"><form
								action="<c:url value="/j_spring_security_logout" />">

								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="Logout" />
									</div>
								</div>

							</form></td>

						</td>
					</TR>
				</TABLE>


			</div>
		</div>




		<div id="navigationwrap">
			<div id="navigation">
				<p></p>
				<h1>${user.userName}'s ${folderType} Folder Page</h1>
				<h3>${warningPublicPageMessage}</h3>
				<h5>${message}</h5>
			</div>
		</div>

		<div id="leftcolumnwrap">
			<div id="leftcolumn">

				<h5>Welcome to the ${folderType} manager page.</h5>
				<h5>Simply click on a file in order to view available actions</h5>
			</div>
		</div>


		<div id="contentwrap">
			<div id="content">

				<h3>Files found in ${user.userName} ${folderType} folder</h3>
				<c:forEach items="${filesFound}" var="file">
				
					<c:url value="/userpage/${user.userName}/${folderType}/handlefile/${file}" var="fileLocation" />
				
					<div style="text-align: center;">
				 	<h6><a href="${fileLocation}">   - ${file}</a>  </h6>
				 </div>
			   
		
				</c:forEach>


			</div>
		</div>


		<div id="footerwrap">
			<div id="footer">

				<TABLE BORDER="0">
					<TR>
						<td width=10>
						<td><h4>
								Logged in as:
								<%=request.getRemoteUser()%></h4></td>
						<td width=810>
							<h4>Role: ${user.userRole}</h4>
						</td>
					</TR>
					</div>
					</div>
</body>
</html>
