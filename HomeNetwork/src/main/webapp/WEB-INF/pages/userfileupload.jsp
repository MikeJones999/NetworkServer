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
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Upload Files page</title>



</head>
<body>

	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Upload Files to Public folder</h1>

				<h3>Add or remove files from your public folder</h3>


				<form method="POST" action="<c:url value="/userpage" />">
					<button type="submit">User home page</button>
				</form>

			</div>
		</div>
	</section>
	<section></section>
	
	<h1>${messageWarning}</h1>


	<form:form method="post" action="fileupload" commandName="fileManager"
		enctype="multipart/form-data">

		<p>Browse and Select files from your computer to upload to your
			public folder</p>
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
		<!--  <input type="submit" value="Upload" /> -->
	</form:form>



</body>
</html>