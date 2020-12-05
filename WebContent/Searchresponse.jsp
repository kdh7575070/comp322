<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="service.MovieService" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
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
	ArrayList<String> movie_list = new ArrayList<String>();
	MovieService movieservice = new MovieService();
	movie_list = movieservice.search_movie((String)session.getAttribute("User_id"), request.getParameter("title"));
	
	
	if(movie_list.size() != 0) {
		PrintWriter script = response.getWriter();
		%>
		<form action ="ShowMovieInfo.jsp" method = "POST">
			<select name="movie_title">
		<%
			for(String s :movie_list) {
		%>
				<option value="<%=s%>"><%=s%></option>
		<%
			}
		%>		
			</select>
			<input type = "submit" value="Submit" />
		</form>
		<%
	}else {

		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('No such a movie. Search Again!')");
		script.println("history.back()");
		script.println("</script>");

	}
	
 
%>
	
</body>
</html>