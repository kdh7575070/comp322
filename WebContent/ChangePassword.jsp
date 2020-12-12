<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.Date" %>
<%@ page import="entity.Account" %>
<%@ page import="service.AccountService" %>
<html lang="en">
<head>
    <meta charset="EUC-KR">
    <title>COMP322 : Introduction to Databases</title>
</head>
<body>
<%
	String pw = request.getParameter("pw");
	
	String User_id = (String)session.getAttribute("User_id");
	
	int rs = AccountService.update_user_pwd(User_id,pw);
	
	if(rs == 1){
		PrintWriter script = response.getWriter();
		
		script.println("<script>");
		script.println("alert('Update successful!')");
		script.println("location.href = 'Main.jsp'");
		script.println("</script>");

	}

%>
</body>
</html>