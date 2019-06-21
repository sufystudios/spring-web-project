<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>

Assignment1 Dating App
</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="./resources/css/style.css" type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div class="jumbotron text-center">
    <h1 class="green">Welcome to Dating App</h1>

  	<h1>Welcome ${advertiser.login }</h1>
  	</div>

		<div class="container-fluid text-center">
    <h3>List of Customers</h3>
    <table>
	<c:forEach var="tempCustomer" items="${customer }">
		<tr>
			<td>${tempCustomer.login }</td>
			<td>${tempCustomer.age }</td>
			<td>${tempCustomer.salary }</td>
			<td>${tempCustomer.gender }</td>
			</tr>
			</c:forEach>
			</table>
</div>


</body>
</html>