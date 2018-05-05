<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Goods"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>goods</title>
</head>
<body>

	<%
		List<Goods> goodsList = (ArrayList<Goods>) request.getAttribute("glist");

		for (Goods ref : goodsList) {
			out.print("Id: " + ref.getGoodsId());
			out.print("<br/>");
			out.print("Name: " + ref.getGoodsName());
			out.print("<br/>");
			out.print("Price: " + ref.getGoodsPrice());
			out.print("<br/>");
			out.print("GoodsQuantity: " + ref.getGoodsQuantity());
			out.print("<br/>");
			
		}
	%>
</body>
</html>