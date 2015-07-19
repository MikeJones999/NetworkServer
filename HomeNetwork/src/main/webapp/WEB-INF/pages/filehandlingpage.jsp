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
}

p {
	text-align: center;
}

btn_btn-primary {
	color: green;
}
</style>
<title>File handling page</title>
</head>

<body>
	<div id="wrapper">
		<div id="headerwrap">
			<div id="header">

				<TABLE BORDER="0">
					<TR>

						<td><form
								action="<c:url value="/userpage/${user.userName}" />">
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
								action="<c:url value="/userpage/${user.userName}/${folderType}" />">
								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="Back to ${folderType} folder" />
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
				<h1>File handling page</h1>
				<p></p>
				<h5>${messageCopied}</h5>

			</div>
		</div>

		<div id="leftcolumnwrap">
			<div id="leftcolumn">

				<h5>Welcome to the ${folderType} individual File manager page.</h5>
				<h5>Here you can select specific actions you can perform in
					relation to the specified file</h5>
				<p></p>
				<h5>Note: You can only copy the link of a file that is placed
					in your public folder.</h5>

			</div>
		</div>


		<div id="contentwrap">
			<div id="content">


				<h5>You have clicked on file:-</h5>
				<h3>${file}</h3>
				<h5>This file allows for the following actions...</h5>


				<form
					action="<c:url value="/userpage/${user.userName}/${folderType}/download/${file}" />">
					<div class="form-group">
						<div class="col-lg-offset-0 col-lg-10">
							<input type="submit" id="btnAdd" class="btn btn-primary"
								value="Download file" />
						</div>
					</div>
				</form>
				<p></p>
				<form
					action="<c:url value="/userpage/${user.userName}/${folderType}/delete/${file}" />">
					<div class="form-group">
						<div class="col-lg-offset-0 col-lg-10">
							<input type="submit" id="btnAdd" class="btn btn-primary"
								value="  Delete file  " />
						</div>
					</div>
				</form>
				<p></p>
				<c:if test="${folderType == 'public'}">
					<form
						action="<c:url value="/userpage/${user.userName}/public/copyFileLink/${file}" />">
						<div class="form-group">
							<div class="col-lg-offset-0 col-lg-10">

								<input type="submit" id="btnAdd" class="btn btn-primary"
									value="Copy file link" />

							</div>
						</div>
					</form>
				</c:if>
				<p></p>
				<c:if test="${folderType == 'private'}">
					<form
						action="<c:url value="/userpage/${user.userName}/private/copyFiletoPublic/${file}" />">
						<div class="form-group">
							<div class="col-lg-offset-0 col-lg-10">

								<input type="submit" id="btnAdd" class="btn btn-primary"
									value="Copy to Public folder" />

							</div>
						</div>
					</form>
				</c:if>
				<p></p>
				<c:if test="${fileType == 'image'}">
					<form
						action="<c:url value="/userpage/${user.userName}/${folderType}/image/${file}" />">
						<div class="form-group">
							<div class="col-lg-offset-0 col-lg-10">

								<input type="submit" id="btnAdd" class="btn btn-primary"
									value="View Image" />

							</div>
						</div>
					</form>
				</c:if>


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