<!--  http://javahonk.com/spring-mvc-upload-multiple-file/ 11/07/2015  &
http://crunchify.com/spring-mvc-tutorial-how-to-upload-multiple-files-to-specific-location/
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<html>
<head>
<!-- This script was taken from http://crunchify.com/spring-mvc-tutorial-how-to-upload-multiple-files-to-specific-location/ 11/07/2015 -->
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script>
	$(document).ready(
			function() {
				//add more file components if Add is clicked
				$('#addFile')
						.click(
								function() {
									<!--
									var fileIndex = $('#fileTable tr')
											.children().length - 1;
									-->
									$('#fileTable').append(
											'<tr><td><input type="file" name="files['
													+ $('#fileTable tr').length
													+ ']" />' + '</td></tr>');
								});

			});
</script>
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
<title>${folderType}UploadFiles page</title>
</head>

<body>
	<div id="wrapper">
		<div id="headerwrap">
			<div id="header">

				<TABLE BORDER="0">
					<TR>
						<td><form action="<c:url value="/userpage" />">
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
			<p>
			</p>
				<h1>Upload Files to ${folderType} folder</h1>
				<h3>Browse and Select files from your computer to upload to your
					${folderType} folder</h3>
			</div>
		</div>

		<div id="leftcolumnwrap">
			<div id="leftcolumn">

				<h5>Welcome to the file upload page for your ${folderType}
					folder.</h5>
				<p></p>
				<h5>Any files uploaded will be placed in the ${folderType} folder.</h5>
				<p></p>
				<h5>Remember, any file uploaded to public folders can be
					downloaded without log in credentials</h5>
			</div>
		</div>


		<div id="contentwrap">
			<div id="content">

				<h1>${messageWarning}</h1>
				<form:form method="post" action="fileupload"
					commandName="fileManager" enctype="multipart/form-data">


					<p>Press Add button in order to upload multiple files</p>

					<input id="addFile" type="button" value="Add File" />

					<p></p>
					<table id="fileTable">
						<tr>
							<td><input name="files[0]" type="file" /></td>
						</tr>
					</table>
					<br />


					<div align="left">
						<div class="form-group">
							<div class="col-lg-offset-0 col-lg-10">
								<input type="submit" id="btnAdd" class="btn btn-primary"
									value="Upload file(s)" />

							</div>
						</div>
					</div>

				</form:form>


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

