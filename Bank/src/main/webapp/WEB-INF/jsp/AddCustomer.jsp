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
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {

		$('#datepicker').datepicker({
			dateFormat : 'yy-mm-dd'
		});

	});
</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="addcustomer">Add
				Customer</a></li>
		<li><a data-toggle="tab" href="viewcustomer">View Customer</a></li>
		<li><a data-toggle="tab" href="#">Logout</a></li>
	</ul>
	<div class="container">
		<p id="error" style="color: red;"></p>
		<form:form action="savecustomer" method="get" modelAttribute="user">
			<input type="hidden" name="id" value=${user.id } />
			<label for="Name">Name:</label>
			<form:input path="name" class="form-control" />
			<br>
			<label for="Name">Address:</label>
			<form:input path="address" class="form-control" />
			<br>
			<label for="Dob">DoB:</label>
			<form:input path="dob" class="form-control" id="datepicker" />
			<br>
			<label for="email">Email:</label>
			<form:input path="email" class="form-control" />
			<br>
			<label for="mobileno">Mobileno:</label>
			<form:input path="mobileno" class="form-control" maxlength="10" />
			<br>
			<label for="accountno">Account no:</label>
			<form:input path="accountno" class="form-control" maxlength="16" />
			<br>
			<label for="balance">Balance:</label>
			<form:input path="balance" class="form-control" />
			<br>
			<label for="password">Password:</label>
			<form:input path="password" class="form-control" />
			<br>
			<label for="accounttype">Account type:</label>
			<form:radiobutton path="accounttype" value="saving" />Saving 
			<form:radiobutton path="accounttype" value="current" />Current
			<br>
			<label for="role">Role:</label>
			<form:radiobutton path="role" value="admin" />Admin 
			<form:radiobutton path="role" value="user" />User
			<br>
			<input type="submit" value="Save" class="btn btn-primary" />
		</form:form>
	</div>
</body>
</html>