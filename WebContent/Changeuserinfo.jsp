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
	if( (request.getParameter("fn").equals("")) || (request.getParameter("ln").equals("")) || (request.getParameter("pn").equals("")) ){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('Fill out necessary information!')");
		script.println("history.back()");
		script.println("</script>");
		
	}

	String fn = request.getParameter("fn");
	String ln = request.getParameter("ln");
	String pn = request.getParameter("pn");
	Date bd = java.sql.Date.valueOf(request.getParameter("bd"));
	String sx = request.getParameter("sx");
	String ad = request.getParameter("ad");
	int jb = Integer.parseInt(request.getParameter("jb"));
	
	Account new_account = new Account();
	
	new_account.setFirst_name(fn);
	new_account.setLast_name(ln);
	new_account.setPhone_number(pn);
	new_account.setBirthday(bd);
	new_account.setSex(sx);
	new_account.setAddress(ad);
	new_account.setJob(jb);
	new_account.setUser_id((String)session.getAttribute("User_id"));
	
	int rs = AccountService.update_user_info(new_account);
	
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