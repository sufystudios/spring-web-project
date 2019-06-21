<!DOCTYPE html>
<html>
<head>
<title>

Assignment1 Dating App
</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

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
    <p> please login to the dating service enter no password to create a new user</p>
    <form:form action="login" modelAttribute="user">
    Username:<form:input path="username" />
    Password:<form:password path="password" />
    </form:form>
    </div>
    <div class="col-xs-3">
    </div>
    </div>
</div>

</body>
</html>