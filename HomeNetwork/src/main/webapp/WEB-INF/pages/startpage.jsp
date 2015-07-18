<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<link href="<c:url value="/resources/myLayout/css/873366.css" />"
	rel="stylesheet" type="text/css" />
<style>
h1 {
	text-align: center;
}

h3 {
	text-align: center;
}

p {
	text-align: center;
}

.floated {
	float: left;
	margin-right: 5px;
}
</style>
<title>Welcome page - Home Server</title>
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
							
											value="User Login" />
									</div>
								</div>
							</form></td>

						<td><form action="<c:url value="/adminpage" />">
								<div class="form-group">
									<div class="col-lg-offset-0 col-lg-10">
										<input type="submit" id="btnAdd" class="btn btn-primary"
								
											value="Admin Login" />

									</div>
								</div>
							</form></td>
					</TR>
				</TABLE>
			</div>
		</div>

		<div id="navigationwrap">
			<div id="navigation">

				<h1>${message1}</h1>
				<h3>${message2}</h3>
				<h3>${message3}</h3>

		
			</div>
		</div>



		<div id="leftcolumnwrap">
			<div id="leftcolumn"></div>
		</div>
		<div id="contentwrap">
			<div id="content">
				<p>
				<h3>Welcome to The Home Network Server.</h3>
				</p>

				<p><h3>${response}</h3></P>
			</div>
		</div>
		<div id="footerwrap">
			<div id="footer">
				<p>This is the Footer</p>
			</div>
		</div>
	</div>
</body>














</html>