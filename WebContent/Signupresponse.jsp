<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="service.AccountService" %>
<%@ page import="entity.Account" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Calendar" %>

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
	
		String url = "jdbc:postgresql://localhost/movietest";
		String uid = "postgres";
		String pwd = "comp322";
		String driver = "org.postgresql.Driver";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		
	} catch (Exception e) {
	
		e.printStackTrace(); // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占�
	}
	
	//占십쇽옙占쏙옙占쏙옙 占쏙옙占쏙옙
	
	if( (request.getParameter("id").equals("")) || (request.getParameter("pw").equals("")) || (request.getParameter("fn").equals("")) || (request.getParameter("ln").equals("")) || (request.getParameter("pn").equals("")) ){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('Fill out necessary information!')");
		script.println("history.back()");
		script.println("</script>");
		
	}
	int sx = 0;
	int age = 30;
	
	Account a1 = new Account();
	a1.setUser_id(request.getParameter("id"));
	a1.setPassword(request.getParameter("pw"));
	a1.setFirst_name(request.getParameter("fn"));
	a1.setLast_name(request.getParameter("ln"));
	a1.setPhone_number(request.getParameter("pn"));
	if(!(request.getParameter("bd").equals(""))){
		a1.setBirthday(java.sql.Date.valueOf(request.getParameter("bd")));
		int b_year = Integer.parseInt(request.getParameter("bd").substring(0,4));
		int c_year = Calendar.getInstance().get(Calendar.YEAR);
		age = c_year - b_year;
	}
	if(!(request.getParameter("sx").equals(""))){
		a1.setSex(request.getParameter("sx"));
		if(a1.getSex().equals("M")) sx = 1;
	}
	if(!(request.getParameter("ad").equals(""))) a1.setAddress(request.getParameter("ad"));
	if(!(request.getParameter("jb").equals(""))) a1.setJob(Integer.parseInt(request.getParameter("jb")));

	AccountService accountService = new AccountService();

	String result = "" + accountService.create_account(a1);
	
	
	//회占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙

	if(result.equals("1")){
		session.setAttribute("User_id", a1.getUser_id());
		session.setAttribute("New_user", 1);
		session.setAttribute("New_user_sx", sx);
		session.setAttribute("New_user_age", age);
		session.setAttribute("New_user_job", a1.getJob());
		System.out.println(sx);
		System.out.println(age);
		System.out.println(a1.getJob());
		
		PrintWriter script = response.getWriter();
		
		script.println("<script>");
		script.println("alert('Signed up successful!')");
		script.println("location.href = 'Main.jsp'");
		script.println("</script>");
	}
	
	if(result.equals("0")){

		PrintWriter script = response.getWriter();
		
		script.println("<script>");
		script.println("alert('This Id already exists!')");
		script.println("history.back()");
		script.println("</script>");
	}

%>
	
</body>
</html>