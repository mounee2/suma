<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.virtusa.buyit.dto.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="BuyitController?action=place_order" method="post">
<table>
		<%
		
			ArrayList<Product> productsList = (ArrayList<Product>) request.getAttribute("products");

			for (Product product : productsList) {
		%>
		<tr>
			<td><input type="checkbox" name="products" value="<%= product.getProductId() %>"></td>
			<td><%=product.getProductId()%></td>
			<td><%=product.getDescription()%></td>
			<td><%=product.getFinish()%></td>
			<td><%=product.getStandardPrice()%></td>
			<td><input name="<%= product.getProductId() %>" type="text"  ></td>
		</tr>
		<%
			} 
		%>
	</table>
	<input type="submit" value="Place Order">
	</form>
</body>
</html>