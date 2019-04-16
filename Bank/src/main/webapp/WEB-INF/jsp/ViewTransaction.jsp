<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a data-toggle="tab" href="addtransaction">Add
				Transaction</a></li>
		<li class="active"><a data-toggle="tab" href="viewtransaction">View
				Transaction</a></li>
		<li><a data-toggle="tab" href="logout">Logout</a></li>
	</ul>
	<div class="table-responsive">
		<table class="table">
			<tr>
				<th>Id</th>
				<th>Transaction Type</th>
				<th>Transaction date</th>
				<th>Receipt Account no</th>
				<th>Amount</th>
			</tr> 
			<c:forEach var="transaction" items="${transaction}">
		<tr>
					<td>${transaction.id}</td>
					<td>${transaction.transactiontype}</td>
					<td>${transaction.transactiondate}</td>
					<td>${transaction.recepientaccno}</td>
					<td>${transaction.amount}</td>
				</tr> 
				
			</c:forEach>
		</table>
	</div>
</body>
</html>