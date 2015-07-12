<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Upload Multiple Files Example</title>

</head>
<body>
    <br>
    <br>
    <div align="left">
 
        <h1>File upload complete</h1>
        <p>Files uploaded successfully.</p>
        <ol>
        
            <c:forEach items="${files}" var="file">
            <li> - ${file}</li> 
            </c:forEach>
        </ol>
     
        <br />
        <br />
       
    </div>
</body>
</html>