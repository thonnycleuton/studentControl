<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<%@ page import="nascimentot.model.*" %>
<% Student aStudent = 
     (Student) session.getAttribute("Student"); %>
	
<title>Bradshaw Marina Student Options</title>
</head>
<body>
<center>
<h1><em><font color = "red">Bradshaw Marina</font></em></h1>
<hr>
<img src = "images/smallimage1.gif">
<img src = "images/smallimage2.gif">
<img src = "images/smallimage3.gif">
<img src = "images/smallimage4.gif">
<img src = "images/smallimage5.gif">
<img src = "images/smallimage6.gif">
<img src = "images/smallimage7.gif">
<img src = "images/smallimage8.gif">
<img src = "images/smallimage9.gif">
<img src = "images/smallimage10.gif">
<hr>
<h2>Hello
<%= aStudent.getFirstName() %>!
</h2>
<p>
<h3><strong>Would you like to:</strong></h3>
<table>
<tr><td><li>
<a href="index.html"> Return to the Bradshaw Marina home page</a>
</li></td></tr>
</table>
</p>
</center>
</body>
</html>


