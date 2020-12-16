<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.virtusa.buyit.dto.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
out.println(request.getAttribute("productAdded"));

%>
	<table>
		<%
		
			ArrayList<Product> productsList = (ArrayList<Product>) request.getAttribute("products");

			for (Product product : productsList) {
		%>
		<tr>
		
			<td><%=product.getProductId()%></td>
			<td><%=product.getDescription()%></td>
			<td><%=product.getFinish()%></td>
			<td><%=product.getStandardPrice()%></td>
		</tr>
		<%
			} 
		%>
	</table>


	<form action="BuyitController?action=add_products" method="post">
		Product Description <input type="text" name="productDescription"
			id="productDescription"><br> <br> Product Finish <input
			type="text" name="productFinish" id="productFinish"><br>
		<br> Standard Price <input type="text" name="standardPrice"
			id="standardPrice"><br> <br> <input type="submit"
			value="Add Product" style="font-weight: bold" />
	</form>



</body>
</html>