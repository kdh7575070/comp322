<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<html lang="en">
<head>
    <meta charset="EUC-KR">
    <title>COMP322 : Introduction to Databases</title>
</head>
<body>
    <h2> ---- SIGNUP ---- </h2>
    <h4 style="color:red;"> ID, PW, Name and Phone number is necessary. </h4>
    <form action ="Signupresponse.jsp" method = "POST">
        ID: <input type = "text" name = "id" />
        <br />
    	PW: <input type = "text" name = "pw" />
    	<br />
    	First Name: <input type = "text" name = "fn" />
    	<br />
    	Last Name: <input type = "text" name = "ln" />
    	<br />
    	Phone Number(01012345678): <input type = "text" name = "pn" />
    	<br />
    	Birth date(2000-01-01): <input type = "text" name = "bd" />
    	<br />
    	Sex:
    	<select name="sx">
    		<option value = "" selected>select option</option>
    		<option value = "F">Female</option>
    		<option value = "M">Male</option>
    	</select>
    	<br/>
    	Address: <input type = "text" name = "ad" />
    	<br />
    	Job: 
    	<select name="jb">
       		<option value = "" selected>select option</option> 	
    		<option value = "1">academic/educator</option>
    		<option value = "2">artist</option>
    		<option value = "3">clerical/admin</option>
    		<option value = "4">college/grad student</option>
    		<option value = "5">customer service</option>
    		<option value = "6">doctor/health care</option>
    		<option value = "7">executive/managerial</option>
    		<option value = "8">farmer</option>
    		<option value = "9">homemaker</option>
    		<option value = "10">K-12 student</option>
    		<option value = "11">lawyer</option>
    		<option value = "12">programmer</option>
    		<option value = "13">retired</option>
    		<option value = "14">sales/marketing</option>
    		<option value = "15">scientist</option>
    		<option value = "16">self-employed</option>
    		<option value = "17">technician/engineer</option>
    		<option value = "18">tradesman/craftsman</option>
    		<option value = "19">unemployed</option>
    		<option value = "20">writer</option>
    	</select>
    	<br/>
    	<input type = "submit" value="Submit" />
	</form>
</body>
</html>