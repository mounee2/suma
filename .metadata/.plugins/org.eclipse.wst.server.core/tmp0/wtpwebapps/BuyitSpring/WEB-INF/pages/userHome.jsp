<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.virtusa.buyit.dto.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.springframework.web.servlet.ModelAndView;"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div align="right">
		<form:form modelAttribute="customers">
			<form:input  path="id" readonly="true"/>
			<form:input path="userName" readonly="true" />
		</form:form>

	</div>
	<div>
		<form:form action="place_order" modelAttribute="products">

			<div>
				<table>
					<c:forEach varStatus="pd" var="product"
						items="${products.products}">
						<tr>
							<td><input type="checkbox" name="productslis" /></td>
							<td><label> Product Id:</label> <form:input type="number"
									path="products[${pd.index}].productId" name="productId" readonly="true" /></td>
							<td><label> Product Description:</label> <form:input
									type="text" path="products[${pd.index}].description"
									name="description" readonly="true" /></td>

							<td><label> Product Finish:</label> <form:input type="text"
									path="products[${pd.index}].finish" name="finish" readonly="true" /></td>

							<td><label> Price:</label> <form:input type="number"
									path="products[${pd.index}].standardPrice" name="standardPrice" readonly="true"  /></td>
							<td><input name="quantity" type="number" /></td>
						</tr>
					</c:forEach>
				</table>
				<input type="submit" value="Place Order">
			</div>
		</form:form>
	</div>
</body>
</html>