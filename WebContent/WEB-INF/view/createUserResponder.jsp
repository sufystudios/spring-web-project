<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>

Assignment1 Dating App
</title>
<link rel="stylesheet" type="text/css" 

href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />

</head>
<body>
<div class="container">
    <div class="row">
    <div class="col-xs-12">
    <h1 class="green">Welcome to Dating App</h1>

    </div>
    </div>
    <div class="row">
    <div class="col-xs-3">
    </div>
    <div class="col-xs-6">
  	<p>User ${responder.login } Created</p>
  	<p>Password ${responder.password } </p>
    </div>
    <div class="col-xs-3">
    </div>
    </div>
    <div class="row">
    <div class="col-xs-12">
 <form:form action="login" modelAttribute="user">
    Username:<form:input path="username" /><br/><br />
    Password:<form:password path="password" /><br /><br />
    
    <input type="submit" />
    </form:form>
    
    </div>

</div>

</div>

</body>
</html>