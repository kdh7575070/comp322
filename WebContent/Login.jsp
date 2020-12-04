<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>

<html lang="en">
<head>
    <meta charset="EUC-KR">
    <title>COMP322 : Introduction to Databases</title>
</head>
<body>
    <h2> ---- LOGIN ---- </h2>
    <form action ="Loginresponse.jsp" method = "POST">
        id: <input type = "text" name = "id" />
    	pw: <input type = "text" name = "pw" />
    	<input type = "submit" value="Submit" />
	</form>
</body>
</html>