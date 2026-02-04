<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ví dụ 2.2</title>
<style>h2{text-weight: bold;}</style>
</head>
<body>	
	<%! int x = 10; int y = 10; int z = 0; %>
	<% y = 200;
		z = x + y;
		out.append("Kết quả là :");
		out.append(String.valueOf(z));	
	%>
	<h2>Hoặc ta có thể xuất kiểu expression</h2>
	<hr>
	<%="Kết quả là" %>
	<%=z %>
</body>
</html>