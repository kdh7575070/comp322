<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html lang="en">
<%
	session.removeAttribute("User_id");
	session.removeAttribute("New_user");
	session.removeAttribute("New_user_sx");
	session.removeAttribute("New_user_age");
	session.removeAttribute("New_user_job");
	response.sendRedirect("Main.jsp");
%>