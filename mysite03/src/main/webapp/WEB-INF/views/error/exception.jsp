<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Oops!</h1>
	<p>
		죄송합니다.<br/>
		예기치 않은 서비스 장애가 발생하였습니다.<br/>
		=============================================
	</p>
		<pre style="color:red">
${errors }
		</pre>
	
</body>
</html>