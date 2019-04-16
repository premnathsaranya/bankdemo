<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<script type="text/javascript">
	function validate() {
		if (document.getElementById("user_name").value == "") {
			document.getElementById("error").innerHTML = "<center>Username required</center>";
			return false;
		} else if (document.getElementById("user_passwd").value == "") {
			document.getElementById("error").innerHTML = "<center>Password required</center>";
			return false;
		} else {
			return true;
		}
	}
</script>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Login Page</a>
		</div>

	</div>
	</nav>
	<div class="container">
		<p id="error" style="color: red;"></p>
		<form:form action="checkuser" method="post" modelAttribute="user">
			<label for="Name">Name:</label>
			<form:input path="name" class="form-control"/>
			<br>
			<label for="password">Password:</label>
			<form:password path="password" class="form-control" />
			<br>
			<label for="role">Role:</label>
			<form:radiobutton path="role" value="admin" />Admin 
			<form:radiobutton path="role" value="user" />User
			<br>
			<br>
			<input type="submit" value="Save" onclick="return validate()"
				class="btn btn-primary" />
		</form:form>
	</div>
</body>
</html>