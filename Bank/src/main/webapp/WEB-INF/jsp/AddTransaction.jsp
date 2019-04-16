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

	function validate() {
		if ((document.getElementById("currentamount").value) >= (document
				.getElementById("amount").value)) {
			return true;
		} else {
			//document.write(document.getElementById("currentamount").value +"  "+document.getElementById("amount").value);
			document.getElementById("error").innerHTML = "<center>Insufficient Balance</center>";
			return false;
		}
	}
</script>

</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="addtransaction">Add
				Transaction</a></li>
		<li><a data-toggle="tab" href="viewtransaction">View
				Transaction</a></li>
		<li><a data-toggle="tab" href="logout">Logout</a></li>
	</ul>
	<div class="container">
		<p id="error" style="color: red;"></p>
		<form:form action="maketransaction" method="post"
			modelAttribute="transaction">
			<input type="hidden" id="currentamount"
				value='<%=session.getAttribute("amount")%>'>
			<%-- 	<label for="transactiontype">Transaction Type:</label>
			<form:input path="transactiontype" class="form-control" />
			<br> --%>
			<label for="transactiontype">Transaction Type:</label>
			<form:radiobutton path="transactiontype" value="saving" />Saving 
			<form:radiobutton path="transactiontype" value="current" />Current
			<br>
			<label for="accountno">Account No:</label>
			<form:input path="accountno" class="form-control" disabled="true"
				value='<%=session.getAttribute("accountno")%>' />
			<br>
			<label for="recepientaccno">Recepient Account No:</label>
			<form:input path="recepientaccno" class="form-control" />
			<br>
			<label for="amount">Amount</label>
			<form:input path="amount" class="form-control" />
			<br>
			<input type="submit" value="Make Transaction" class="btn btn-primary" />
		</form:form>
	</div>
</body>
</html>