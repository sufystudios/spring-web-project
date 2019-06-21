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
  	<a href="/">Return to login screen</a>
  	</div>
<div class="container-fluid text-center">
			
				<h3>Update Details
</h3>
			
		
			<form:form action="updateAdvertiser" modelAttribute="advertiser" class="form-group">
 			<div class="col-xs-5"></div>
			<div class="col-xs-7 bg-grey text-left"><br/>
			<h1><span class="label label-default">${ advertiser.login}</span></h1>
		<form:hidden path="login" />
   <div class="input-group"><span class="label label-default">Password:</span><form:password path="password" class="form-control" placeholder="Password"/>
				<form:errors path="password" cssClass="error" /></div>
			
	
			 <div class="input-group"><span class="label label-default">Advertisement:</span><form:input path="advertisement" class="form-control" placeholder="Advertisement"/>
			<form:errors path="advertisement" cssClass="error" /></div>
			<div class="input-group"><span class="label label-default">Age:</span><form:input path="age" class="form-control" placeholder="Age"/>
			<form:errors path="age" cssClass="error" /></div>
		
			<div class="input-group"><span class="label label-default">Gener:</span><form:select path="gender" class="">
				<form:option value="M"/>
				<form:option value="F"/>
			</form:select>
		
			<div class="input-group"><span class="label label-default">Income:</span><form:input path="income" class="form-control" placeholder="Income"/>
			<form:errors path="income" cssClass="error" /></div>
		
		
			
			<br />
			<h3>Requirements:</h3>
			<div class="input-group"><span class="label label-default">Required Gender:</span>
			<form:select path="gender" class="form-control">
				<form:option value="M"/>
				<form:option value="F"/>
			</form:select>
			</div>
			<div class="input-group"><span class="label label-default">Required Lowage:</span>
			<form:input path="req.lowage" class="form-control"/>
			<form:errors path="req.lowage" cssClass="error" />
			</div>
			<div class="input-group"><span class="label label-default">Required maxage:</span>
			<form:input path="req.highage" class="form-control"/>
			<form:errors path="req.highage" cssClass="error" />
			</div>
			<div class="input-group"><span class="label label-default">Required low income:</span>
			<form:input path="req.lowincome" class="form-control"/>
			<form:errors path="req.lowincome" cssClass="error" />
			</div>
			<div class="input-group"><span class="label label-default">Required max income</span>
			<form:input path="req.highincome" class="form-control"/>
			<form:errors path="req.highincome" cssClass="error" />
			</div></div>
			<div class="col-xs-12 text-center">
			<br /> <input type="submit" value="Update" class="btn-danger" />
			</div>
			</form:form>
		</div>
		<div class="container-fluid text-center">
    <h3>Replies to advertisement</h3>
    <ul>
    <c:forEach var="reply" items="${replies }">
    	<li>Sent from:${reply.owner.login }<br />
    	Message: ${reply.message }<br />
    	<a href="deleteReply?id=${reply.id }&login=${advertiser.login}">Delete</a>
    </c:forEach>
    </ul>
	
</div>


</body>
</html>
