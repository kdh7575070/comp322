<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html lang="en">
<%
	session.removeAttribute("User_id");
	response.sendRedirect("Main.jsp");
%>