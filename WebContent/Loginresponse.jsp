<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="service.AccountService" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page language="java" import= "java.text.*, java.sql.*" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="EUC-KR">
    <title>TEAMPP4-login</title>
</head>
<body>
<%

	try {
	
		String url = "jdbc:postgresql://localhost/movietest";
		String uid = "postgres";
		String pwd = "comp322";
		String driver = "org.postgresql.Driver";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		
	} catch (Exception e) {
	
		e.printStackTrace(); // 오류가 무엇인지 출력
	}

	AccountService accountService = new AccountService();
	String User_id = request.getParameter("id");
	String result = "" + accountService.login(User_id, request.getParameter("pw"));

	//로그인 성공

	if(!result.equals("")){
		session.setAttribute("User_id", User_id);
		
		

		PrintWriter script = response.getWriter();
		
		script.println("<script>");
		script.println("alert('Login successful!')");
		script.println("location.href = 'Main.jsp'");
		script.println("</script>");

	}

	//로그인 실패

	else {

		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('Login unsuccessful!')");
		script.println("history.back()");
		script.println("</script>");

	}

%>
	
</body>
</html>