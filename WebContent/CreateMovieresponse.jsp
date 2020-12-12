<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.AdminService" %>
<%@ page import="entity.Movie" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page language="java" import= "java.text.*, java.sql.*" %>
<% request.setCharacterEncoding("UTF-8"); %>

<head>
    <meta charset="EUC-KR">
    <title>TEAMPP4-createmovie</title>
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


	if( (request.getParameter("mt").equals("")) || (request.getParameter("type").equals("")) || (request.getParameter("ia").equals("")) ){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('Fill out necessary information!')");
		script.println("history.back()");
		script.println("</script>");
		
	}

	Movie new_movie = new Movie();
	if(!(request.getParameter("mt").equals(""))) new_movie.setMovie_title(request.getParameter("mt"));
	if(!(request.getParameter("type").equals(""))) new_movie.setType(request.getParameter("type"));
	if(!(request.getParameter("ia").equals(""))) new_movie.setIs_adult(Boolean.parseBoolean(request.getParameter("ia")));
	if(!(request.getParameter("pid").equals("")))  new_movie.setParent_id(Integer.parseInt(request.getParameter("pid")));
	if(!(request.getParameter("sn").equals(""))) new_movie.setSeries_number(Integer.parseInt(request.getParameter("sn")));
	if(!(request.getParameter("dfn").equals(""))) new_movie.setDirector_first_name(request.getParameter("dfn"));
	if(!(request.getParameter("dln").equals(""))) new_movie.setDirector_last_name(request.getParameter("dln"));
	if(!(request.getParameter("sy").equals(""))) new_movie.setStart_year(java.sql.Date.valueOf(request.getParameter("sy")));
	if(!(request.getParameter("rt").equals(""))) new_movie.setRuntime(Integer.parseInt(request.getParameter("rt")));

	AdminService adminService = new AdminService();
	
	String result = "" + adminService.create_movie(new_movie);

	if(result.equals("1")){
		PrintWriter script = response.getWriter();
		
		script.println("<script>");
		script.println("alert('Create successful!')");
		script.println("location.href = 'Admin.jsp'");
		script.println("</script>");

	}
%>
</body>
</html>