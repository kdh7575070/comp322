<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="service.AccountService" %>
<%@ page import="entity.Account" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page language="java" import= "java.text.*, java.sql.*" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="EUC-KR">
    <title>TEAMPP4-signup</title>
</head>
<body>
<%

	try {
	
		String url = "jdbc:postgresql://localhost/testdb";
		String uid = "testdb";
		String pwd = "testdb";
		String driver = "org.postgresql.Driver";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		
	} catch (Exception e) {
	
		e.printStackTrace(); // 오류가 무엇인지 출력
	}
	
	//필수정보 누락
	
	if( (request.getParameter("fn").equals("")) || (request.getParameter("ln").equals("")) || (request.getParameter("pn").equals("")) ){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('Fill out necessary information!')");
		script.println("history.back()");
		script.println("</script>");
		
	}

	Account a1 = new Account();
	a1.setUser_id((String)session.getAttribute("User_id"));
	a1.setFirst_name(request.getParameter("fn"));
	a1.setLast_name(request.getParameter("ln"));
	a1.setPhone_number(request.getParameter("pn"));
	if(!(request.getParameter("bd").equals(""))) a1.setBirthday(java.sql.Date.valueOf(request.getParameter("bd")));
	if(!(request.getParameter("sx").equals(""))) a1.setSex(request.getParameter("sx"));
	if(!(request.getParameter("ad").equals(""))) a1.setAddress(request.getParameter("ad"));
	if(!(request.getParameter("jb").equals(""))) a1.setJob(Integer.parseInt(request.getParameter("jb")));

	AccountService accountService = new AccountService();

	String result = "" + accountService.update_user_info(a1);

	if(result.equals("1")){

		PrintWriter script = response.getWriter();

		script.println("<script>");
		script.println("alert('Chnage successful!')");
		script.println("history.back()");
		script.println("</script>");

	}
%>
	
</body>
</html>