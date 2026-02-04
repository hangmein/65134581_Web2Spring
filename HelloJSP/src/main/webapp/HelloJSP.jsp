<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloJSP</title>
<style> 
	body {
	text-align:center;
	font-family: Arial,sans-serif;
	}	
</style>
</head>
<body>
	<h1>Helllo JSP</h1>
	<p>Time server: <%=new Date() %><p>
</body>
</html>