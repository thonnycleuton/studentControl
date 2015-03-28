<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
	<link rel="stylesheet" href="css/form.css" type="text/css" media="all" />
	<link href='http://fonts.googleapis.com/css?family=Ubuntu:400,500,700' rel='stylesheet' type='text/css' />
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/form.js"></script>
	
	<%@ page import="nascimentot.model.*"%>
	<%
		String link, link2 ,menuLink , caption, message = "Login", message2="";
		Student aStudent = (Student) session.getAttribute("Student");
		System.out.print(aStudent);
		
		if (aStudent != null){
			message = "Log out";
			message2 = "Change Password";
			link = "./LogoutServlet";
			link2="passwordChange.jsp";
			menuLink = "VerifyCustInfo.jsp";
			caption = "Perfil";
		}else{
			link = "login.jsp";
			menuLink = link;
			link2 = "#";
			caption = "Login";
		}
	%>
	<title><%= title %></title>
	<!--
	NAme:
	File: 
	Date:
	Description: 
	 -->
</head>
<header id="header">
	<h1 id="logo">
		<a href="./">Durham College</a>
	</h1>
	<h2 class="search">
		<a href="<%=link%>"><%= message %></a>
		<a href="<%=link2%>"><%= message2 %></a>
	</h2>
</header>
	<nav id="navigation"> <a href="#" class="nav-btn">HOME<span class="arr"></span></a> 
	<!-- Menu ---->
		<ul>
			<li class="active"><a href="./">HOME</a></li>
			<li><a href="#">Programs & Courses</a></li>
			<li><a href="#">Admissions</a></li>
			<li><a href="#">Student Experience</a></li>
			<li><a href="registration.jsp">Registration</a></li>
			<li><a href="<%=menuLink%>"><%=caption %></a></li>
			<li><a href="#">About Us</a></li>
		</ul>
	</nav>
