<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Supplier"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>goods</title>
</head>
<body>

	<%
		List<Supplier> supplierList = (ArrayList<Supplier>) request.getAttribute("slist");

		for (Supplier ref : supplierList) {
			out.print("Id: " + ref.getSupplierId());
			out.print("<br/>");
			out.print("Name: " + ref.getSupplierName());
			out.print("<br/>");
			out.print("Price: " + ref.getSupplierAddress());
			out.print("<br/>");
			out.print("OrderQuantity: " + ref.getQuantityOrder());
			out.print("<br/>");
			out.print("OrderId: " + ref.getOrderId());
			out.print("<br/>");
			out.print("Amount: " + ref.getAmount());
			out.print("<br/>");
			
		}
	%>
</body>
</html>