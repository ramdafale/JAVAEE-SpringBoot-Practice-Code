<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Customer"%>
<html>
<body>

	<%
		List<Customer> customerList = (ArrayList<Customer>) request.getAttribute("list");

		for (Customer ref : customerList) {
			out.print("Id: " + ref.getCustomerId());
			out.print("<br/>");
			out.print("Name: " + ref.getCustomerName());
			out.print("<br/>");
			out.print("Address: " + ref.getCustomerAddress());
			out.print("<br/>");
			out.print("PaymentMode: " + ref.getPaymentMode());
			out.print("<br/>");
		}
	%>


   <%
         Integer hitsCount = (Integer)application.getAttribute("hitCounter");
         if( hitsCount ==null || hitsCount == 0 ) {
            /* First visit */
            out.println("Welcome to my website!");
            hitsCount = 1;
         } else {
            /* return visit */
            out.println("Welcome back to my website!");
            hitsCount += 1;
         }
         application.setAttribute("hitCounter", hitsCount);
      %>
      <center>
         <p>Total number of visits: <%= hitsCount%></p>
      </center>

</body>
</html>