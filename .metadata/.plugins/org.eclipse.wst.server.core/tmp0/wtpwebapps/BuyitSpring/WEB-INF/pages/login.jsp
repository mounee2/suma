<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
</style>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body background="C:\Users\user\Downloads\login.jpg">
	<div align='center'>
		<form:form action='userCheck'>
			<h1>LOGIN YOUSELF</h1>
			<label id="userName">UserName</label>
			<input type='text' name='userName' id='userName'>
			<br>
			<br>
			<label id="password">Password</label> 
			<input type='password' name='password' id='password'><br> <br> <br>
			<input type='submit' value='LOGIN'>
			<input type='submit' value='REGISTER' formaction='registerCustomer.jsp'>
		</form:form>

	</div>

</body>
</html>