<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="service.AccountService" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page language="java" import= "java.text.*, java.sql.*" %>
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="user" class="entity.Account" scope="page" />
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="userPassword" /> 

<!DOCTYPE html>
<html>

<head>
    <meta charset="EUC-KR">
    <title>TEAMPP4-login</title>
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
	
		e.printStackTrace(); // ������ �������� ���
	}

	AccountService accountService = new AccountService();
	String result = "" + accountService.login(request.getParameter("id"), request.getParameter("pw"));

	//�α��� ����

	if(!result.equals("")){

		PrintWriter script = response.getWriter();

		script.println("<script>");
		script.println("history.back()");
		//script.println("<location.href = 'main.jsp'");
		script.println("</script>");

	}

	//�α��� ����

	else {

		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('�α��ο� �����Ͽ����ϴ�.')");
		script.println("history.back()");
		script.println("</script>");

	}

%>
	
</body>
</html>