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
	p
	{
	text-align
	:
	left;
     }


</style>
<title>File Manager Page</title>
</head>

<body>
	<div id="wrapper">
		<div id="headerwrap">
			<div id="header">

				<TABLE BORDER="0">
					<TR>

						<td><form
								action="<c:url value="/userpage" />">
								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="User home page" />
									</div>
								</div>
							</form></td>
						<td><form
								action="<c:url value="/userpage/${user.userName}/public" />">
								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="Public Folder" />
									</div>
								</div>
							</form></td>
								<td><form
								action="<c:url value="/userpage/${user.userName}/private" />">
								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="Private Folder" />
									</div>
								</div>
							</form></td>

						<td>
							<td width=600><td colsp an="6"><form action="<c:url value="/j_spring_security_logout" />">

								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
											value="Logout" />
									</div>
								</div>

							</form>
						</td>
						
						</td>
					</TR>
				</TABLE>
	
			
			</div>
		</div>




		<div id="navigationwrap">
			<div id="navigation">
					<h1>File Manager Page</h1>
			<h3>Access public/private folders from this page</h3>
			</div>
		</div>

		<div id="leftcolumnwrap">
			<div id="leftcolumn">

				<h5>Welcome to the file manager page.</h5>
				<h5> From this page you can navigate to your public and or private folders</h5>
			</div>
		</div>


		<div id="contentwrap">
			<div id="content">

					<h3>${user.userName}'s Files</h3>
					<p></p>
					<p> - You have : ${privateFolder} files in your Private Folder</p>
					<p></p>
					<p> - You have : ${publicFolder} files in your Public Folder.</p>
					<p> (All public files are available to share via download)</p>
		

			
			</div>
		</div>


		<div id="footerwrap">
			<div id="footer">

				<TABLE BORDER="0">
					<TR>
						<td width=10>
							
						<td><h4>Logged in as: <%=request.getRemoteUser()%></h4>
									</td>
						<td width=810>
						<h4>Role: ${user.userRole} </h4>
						</td>
					</TR>
					</div>
					</div>
			

</body>
</html>