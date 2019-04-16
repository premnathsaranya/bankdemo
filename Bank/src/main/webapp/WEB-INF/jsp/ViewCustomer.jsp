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
		<li><a data-toggle="tab" href="addcustomer">Add Customer</a></li>
		<li class="active"><a data-toggle="tab" href="viewcustomer">View
				Customer</a></li>
		<li><a data-toggle="tab" href="logout">Logout</a></li>
	</ul>
	<%-- <div class="table-responsive">
		<table class="table">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Address</th>
				<th>DOB</th>
				<th>Email Id</th>
				<th>Account No</th>
				<th>Account Balance</th>
				<th>Password</th>
				<th colspan="2">Action</th>
			</tr>
			<c:forEach var="user" items="${user}">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.address}</td>
					<td>${user.dob}</td>
					<td>${user.email}</td>
					<td>${user.accountno}</td>
					<td>${user.balance}</td>
					<td>${user.password}</td>
					<td><a href="Editcustomer?id=${user.id}">Edit</a></td>
					<td>Delete</td>

				</tr>
			</c:forEach>
		</table>
	</div> --%>
<!-- pagination code start -->
	<c:set value="${userList}" var="userPageList" />
	<div class="table-responsive">
		<table class="table">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Address</th>
			<th>DOB</th>
			<th>Email Id</th>
			<th>Account No</th>
			<th>Account Balance</th>
			<th>Password</th>
			<th colspan="2">Action</th>
		</tr>
		<c:forEach items="${userPageList.pageList}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.address}</td>
				<td>${user.dob}</td>
				<td>${user.email}</td>
				<td>${user.accountno}</td>
				<td>${user.balance}</td>
				<td>${user.password}</td>
				<td><a href="Editcustomer?id=${user.id}">Edit</a></td>
				<td>Delete</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<br />	<br />
	<center><c:choose>
		<%-- Show Prev as link if not on first page --%>
		<c:when test="${userPageList.firstPage}">
			<span>Prev</span>
		</c:when>
		<c:otherwise>
			<c:url value="/prev" var="url" />
			<a href='<c:out value="${url}" />'>Prev</a>
		</c:otherwise>
	</c:choose>
	<c:forEach begin="1" end="${userPageList.pageCount}" step="1"
		varStatus="tagStatus">
		<c:choose>
			<%-- In PagedListHolder page count starts from 0 --%>
			<c:when test="${(userPageList.page + 1) == tagStatus.index}">
				<span>${tagStatus.index}</span>
			</c:when>
			<c:otherwise>
				<c:url value="/${tagStatus.index}" var="url" />
				<a href='<c:out value="${url}" />'>${tagStatus.index}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<%-- Show Next as link if not on last page --%>
		<c:when test="${userPageList.lastPage}">
			<span>Next</span>
		</c:when>
		<c:otherwise>
			<c:url value="/next" var="url" />
			<a href='<c:out value="${url}" />'>Next</a>
		</c:otherwise>
	</c:choose>
	</center>
<!-- pagination code end -->
</body>
</html>