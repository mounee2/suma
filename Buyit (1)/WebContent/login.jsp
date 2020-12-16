<!DOCTYPE html>
<html>
<head>
<style type="text/css">
</style>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body background="C:\Users\aarthis\Desktop\background-learner1.jpg">
	<%
		out.println(request.getAttribute("errorMessage"));
	%>
	<div align='center'>
		<form action='BuyitController?action=login' method='post'>
			<h1>LOGIN YOUSELF</h1>
			UserName<input type='text' name='userName' id='userName'><br>
			<br> Password<input type='password' name='password'
				id='password'><br> <br> <br> <input
				type='submit' value='LOGIN'> <input type='submit'
				value='REGISTER' formaction='registerCustomer.jsp'>

		</form>

	</div>
	


</body>
</html>