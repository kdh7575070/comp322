<!-- MovieService.java에 있는 movie_info 메소드를 그대로 가져왔 -->
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
	String url = "jdbc:postgresql://localhost/movietest";
	String uid = "postgres";
	String pwd = "comp322";
	String driver = "org.postgresql.Driver";

	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, uid, pwd);

	
	
	String movie_title = request.getParameter("movie_title");
	
	String sql = "select * from movie natural join movie_genre where movie_title = ?";
	PreparedStatement st = conn.prepareStatement(sql);
	st.setString(1, movie_title);
	
	%>
	<h4>Movie Info</h4>
	<%
	ResultSet rs = st.executeQuery();
	while(rs.next()){
		out.println("    제목 : " + rs.getString(2)+"\n");
		%>
		</br>
		<%
		out.println("    타입 : " + rs.getString(3));
		%>
		</br>
		<%
		if(rs.getString(3).equals("Series")) out.println("    소속 시리즈 : " + rs.getString(4));
		%>
		</br>
		<%
		if(rs.getString(3).equals("Series")) out.println("    시리즈 번호 : " + rs.getString(5));
		%>
		</br>
		<%
		if(rs.getBoolean(6)) out.println ("    성인등급영화");
		else System.out.println ("    전체관람가");
		%>
		</br>
		<%
		out.println("    감독 : " + rs.getString(7) + " " + rs.getString(8));
		%>
		</br>
		<%
		out.println("    상영시작일 : " + rs.getString(9));
		%>
		</br>
		<%
		out.println("    상영시간 : " + rs.getString(10) + "분");
		%>
		</br>
		<%
		out.println("    장르 : " + rs.getString(11));
		%>
		</br>
		<%
	}
	

	sql = "select avg(ratings) from movie natural join rating where movie_title = ?";
	
	st = conn.prepareStatement(sql);
	st.setString(1, movie_title);
	
	rs = st.executeQuery();
	rs.next();
	out.println("    평균평점 : " + rs.getString(1));
	%>
	</br>
	<%
	sql = "select concat(cast_first_name, ' ', cast_last_name), concat(real_first_name, ' ', real_last_name) from (movie natural join starred_by) natural join actor where movie_title = ? ";
	
	st = conn.prepareStatement(sql);
	st.setString(1, movie_title);
	
	rs = st.executeQuery();
	out.print("    출연진정보 : ");
	
	while(rs.next()) {
	
	out.print(rs.getString(1));
	out.print("(" + rs.getString(2) + ") | ");
	}
	
	System.out.println();

	rs.close();
	st.close();
	conn.close();
%>
</body>
</html>