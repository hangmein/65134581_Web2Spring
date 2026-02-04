<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginJSP</title>
	
</head>
<body>
	<h1>KT</h1>
	<%
		String tenDN = request.getParameter("tendn");
	    String mk = request.getParameter("mk");
	    if("HuynhDucNghia".equals(tenDN) && "1".equals(mk))
			response.sendRedirect("UserProfile.html");
		else
			response.sendRedirect("Login.html");
	%>
</body>
</html>