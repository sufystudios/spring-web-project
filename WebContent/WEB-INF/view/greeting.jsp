
<!DOCTYPE html>
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
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="jumbotron text-center">

				<h1 class="green">Welcome to Dating App</h1>

		

			<p>please login to the dating service or create either an Advertiser or Responder
				</p>
			<form:form action="login" modelAttribute="user" class="form-inline form-group" >
    Username:<form:input path="username" placeholder="Login"  class="form-control"/>
				<form:errors path="username" cssClass="error" />
			
    Password:<form:password path="password" placeholder="password" class="form-control" />
				<form:errors path="password" cssClass="error" />
			<div class="input-group-btn">
				<input type="submit"  class="btn btn-danger"/>
	</div>
			</form:form>
	</div>


	<div class="container-fluid text-center">
			<p>
				<h3>Create new Responder</h3><br/>Responders can send messages to advertisers fitting their criteria
			</p>
		
				
			<form:form action="createUserResponder" modelAttribute="responder" class="form-group" >
			<div class="col-xs-5"></div>
			<div class="col-xs-7 bg-grey text-left"><br/>
 <div class="input-group"><span class="label label-default">Username:</span><form:input path="login"  class="form-control" placeholder="Login"/>
				<form:errors path="login" cssClass="error" /></div>
		
   <div class="input-group"><span class="label label-default">Password:</span><form:password path="password" class="form-control" placeholder="Password"/>
				<form:errors path="password" cssClass="error" /></div>
			
	
			 
			<div class="input-group"><span class="label label-default">Age:</span><form:input path="age" class="form-control" placeholder="Age"/>
			<form:errors path="age" cssClass="error" /></div>
		
			<div class="input-group"><span class="label label-default">Gender:</span><form:select path="gender">
				<form:option value="M"/>
				<form:option value="F"/>
			</form:select>
		
			<div class="input-group"><span class="label label-default">Income:</span><form:input path="income" class="form-control" placeholder="Income"/>
			<form:errors path="income" cssClass="error" /></div></div><div class="col-xs-12 text-center">
		<input type="submit"  class="btn-danger"/>
		</div>
			</form:form>
		</div>



<div class="container-fluid text-center">
			
				<p><h3>Create new Advertiser</h3><br/>Advertisers can post an advertisement and their criteria for a partner and wait for responders to reply</p>
			
		
			<form:form action="createUserAdvertiser" modelAttribute="advertiser" class="form-group">
 			<div class="col-xs-5"></div>
			<div class="col-xs-7 bg-grey text-left"><br/>
 <div class="input-group"><span class="label label-default">Username:</span><form:input path="login"  class="form-control" placeholder="Login"/>
				<form:errors path="login" cssClass="error" /></div>
		
   <div class="input-group"><span class="label label-default">Password:</span><form:password path="password" class="form-control" placeholder="Password"/>
				<form:errors path="password" cssClass="error" /></div>
			
	
			 <div class="input-group"><span class="label label-default">Advertisement:</span><form:input path="advertisement" class="form-control" placeholder="Advertisement"/>
			<form:errors path="advertisement" cssClass="error" /></div>
			<div class="input-group"><span class="label label-default">Age:</span><form:input path="age" class="form-control" placeholder="Age"/>
			<form:errors path="age" cssClass="error" /></div>
		
			<div class="input-group"><span class="label label-default">Gender:</span><form:select path="gender">
				<form:option value="M"/>
				<form:option value="F"/>
			</form:select>
			<div class="input-group"><span class="label label-default">Income:</span><form:input path="income" class="form-control" placeholder="Income"/>
			<form:errors path="income" cssClass="error" /></div>
		
		
			
			<br />
			<h3>Requirements:</h3>
			<div class="input-group"><span class="label label-default">Required Gender:</span>
			<form:select path="gender">
				<form:option value="M"/>
				<form:option value="F"/>
			</form:select>
			</div>
			<div class="input-group"><span class="label label-default">Required mininmum age:</span>
			<form:input path="req.lowage" class="form-control"/>
			<form:errors path="req.lowage" cssClass="error" />
			</div>
			<div class="input-group"><span class="label label-default">Required maximum age:</span>
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
			<br /> <input type="submit" class="btn-danger" />
			</div>
			</form:form>
		</div>


</body>
</html>
