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
h1, h2, h3, h5, h6 {
	text-align: center;
}

h4 {
	text-align: right;
	p
	{
	text-align
	:
	center;
}

.floated {
	float: right;
	margin-right: 50px;
}

.btn-group {
	bottom: 15px;
	position: relative;
	top: 15px;
}
</style>
<title>Confirmation of file(s) upload</title>
</head>

<body>
	<div id="wrapper">
		<div id="headerwrap">
			<div id="header">

				<TABLE BORDER="0">
					<TR>

						<td><form
								action="<c:url value="/userpage/${user.userName}/editprofile" />">
								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="Edit User details" />
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
						<td><form action="<c:url value="upload" />">
								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="Back to ${folderType} Uploads" />
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
				<h1>File Upload confirmation page</h1>
			</div>
		</div>

		<div id="leftcolumnwrap">
			<div id="leftcolumn">

				<h5>File Upload confirmation page.</h5>
				<h5>This page will let you know whether a file was uploaded successfully or not.</h5>
				<p></p>
				<h5>If the file already exists at present this system is set not to override. Therefore the file will not be uploaded.</h5>
			</div>
		</div>


		<div id="contentwrap">
			<div id="content">
				<h5>${messageSuccessful}</h5>

				<c:forEach items="${filesUploaded}" var="file">
			- ${file} <br>
				</c:forEach>


				<br /> 

				<h5>${message1}</h5>
				<h5>${message2}</h5>
				<ol>
					<c:forEach items="${filesNotUploaded}" var="file">
			- ${file} <br>
					</c:forEach>
				</ol>
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