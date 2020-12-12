<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page language="java" import="java.text.*, java.sql.*" %>
<%@ page import="service.MovieService" %>
<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>COMP322: Databases</title>
</head>
<body>
<%
	int rating = Integer.parseInt(request.getParameter("rating"));
	
	String User_id = (String)session.getAttribute("User_id");
	String movie_title = (String)session.getAttribute("movie_title");
	session.removeAttribute("movie_title");
	
	int rs = MovieService.movie_rate(User_id,movie_title,rating);
	
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