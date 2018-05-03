<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Customer"%>
<html>
<body>

<% List<Customer> customerList = (ArrayList<Customer>)request.getAttribute("list");
 
    for(Customer ref : customerList)
    {
        out.print("Id: " + ref.getCustomerId());
        out.print("<br/>");
        out.print("Name: " + ref.getCustomerName());
        out.print("<br/>");
        out.print("Address: " + ref.getCustomerAddress());
        out.print("<br/>");
        out.print("<br/>");
    }
 
%>
 

</body>
</html>