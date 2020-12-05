<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>

<html lang="en">
<head>
    <meta charset="EUC-KR">
    <title>COMP322 : Introduction to Databases</title>
</head>
<body>
    <h2> ---- Search ---- </h2>
    <form action ="Searchresponse.jsp" method = "POST">
        Title: <input type = "text" name = "title" />
    	<input type = "submit" value="Submit" />
    	<br />
	    <br />
		<select name="sx">
    		<option value = "" selected>select option</option>
    		<option value = "F">Female</option>
    		<option value = "M">Male</option>
    	</select>
    	<input type = "submit" value="Submit" />
	</form>
</body>
</html>