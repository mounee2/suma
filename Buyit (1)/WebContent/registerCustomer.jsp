<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.virtusa.buyit.dto.Customer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		Object object = request.getAttribute("customer");
		if (object != null) {
			Customer customer = (Customer) object;
	%>
    Registered Successfully<br>
    
	Your Customer ID
	<%=customer.getId()%>

	<%
		}
	%>




<div align='center'>
	<form action="BuyitController?action=register_customer" method='post'>
		<table>
			<tr>
				<th>Name</th>
				<td><input type='text' name='Name' id='Name'></td>
				<td id='errorName' class='error'></td>
			</tr>

			<tr>
				<th>UserName</th>
				<td><input type='text' name='UserName' id='userName'></td>
				<td id='errorUserName'></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input type='password' name='Password' id='Password'></td>
				<td id='errorPassword'></td>
			</tr>
			<tr>
				<th>Confirm Password</th>
				<td><input type='password' name='ConfirmPassword'
					id='ConfirmPassword'></td>
				<td id='errorConfirmPassword'></td>

			</tr>
			<tr>
				<th>Gender</th>
				<td><input type='radio' name='Gender' id='Gender' value="Male">Male<input
					type='radio' name='Gender' id='gender' value="Female">Female</td>
				<td id='errorGender'></td>
			</tr>

			<tr>
				<th>Email</th>
				<td><input type='text' name='Email' id='Email'></td>
				<td id='errorEmail'></td>
			</tr>
			<tr>
				<th>Phone Number</th>
				<td><input type='text' name='MobileNumber' id='MobileNumber'></td>
				<td id='errorPhoneNumber'></td>

			</tr>
			<tr>
				<th>Address</th>
				<td><input type='text' name='Address' id='Address'></td>
				<td id='errorAddress'></td>
			</tr>
			<tr>
				<th>City</th>
				<td><input type='text' name='City' id='City'></td>
				<td id='errorCity'></td>
			</tr>
			<tr>
				<th>State</th>
				<td><input type='text' name='State' id='State'></td>
				<td id='errorState'></td>
			</tr>
			<tr>
				<th>PostalCode</th>
				<td><input type='text' name='PostalCode' id='PostalCode'></td>
				<td id='errorPostalCode'></td>
			</tr>


			<tr>
				<td colspan='3'><input type="submit" value="Register_customer"></td>
			
			
				<td colspan='3'><input type="submit" value="LOGIN" formaction='login.jsp'></td>
			</tr>
		</table>
	</form>

</div>
</body>
</html>