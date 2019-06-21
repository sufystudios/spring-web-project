<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Assignment1 Dating App</title>
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

		<h1>Welcome ${responder.login }</h1>
		<a href="/">Return to login screen</a>
	</div>
	<div class="container-fluid text-center">
			<p>
				<b>Update Details</b>
			</p>
		
				
			<form:form action="updateResponder" modelAttribute="responder" class="form-group" >
			<div class="col-xs-7 text-left">
			<br/>
<h1><span class="label label-default">${responder.login}</span></h1>	
<form:hidden path="login" />	
   <div class="input-group"><span class="label label-default">Password:</span><form:password path="password" class="form-control" placeholder="Password"/>
				<form:errors path="password" cssClass="error" /></div>
			
	
			 
			<div class="input-group"><span class="label label-default">Age:</span><form:input path="age" class="form-control" placeholder="Age"/>
			<form:errors path="age" cssClass="error" /></div>
		
			<div class="input-group"><span class="label label-default">Gender:</span><form:select path="gender" class="form-control">
				<form:option value="M"/>
				<form:option value="F"/>
			</form:select>
		
			<div class="input-group"><span class="label label-default">Income:</span><form:input path="income" class="form-control" placeholder="Income"/>
			<form:errors path="income" cssClass="error" /></div>
		<input type="submit"  value="Update" class="btn-danger"/>
		</div>
			</form:form>
	
	<div class="col-xs-5 text-left">
		<h3>Advertisers Fitting Match</h3>
			<p>${responder.message }</p>
	


		<b>Send a message!!</b><br />
		<br />
		<form:form action="submitreply" modelAttribute="reply" class="form-group" >
	Username: <form:select path="username" class="form-control">
				<c:forEach items="${responder.matches}" var="entry">
					<form:option value="${entry.value.login}" />
				</c:forEach>
			</form:select>
			</br>
	<div class="input-group"><span class="label label-default">Message:</span><form:input path="message" class="form-control"/></div>
<form:hidden path="responder" value="${responder.login }" />
			<input type="submit" value="Send Reply" class="btn-danger" />
		</form:form>
	</div>
	</div>

</body>
</html>
