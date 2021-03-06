<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	
	<title>Login Form</title>	
			<!-- Style -->
			<style>
			.login-form {
			    width: 340px;
			    margin: 50px auto;
			  	font-size: 15px;
			}
			.login-form form {
			    margin-bottom: 15px;
			    background: #f7f7f7;
			    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
			    padding: 30px;
			}
			.login-form h2 {
			    margin: 0 0 15px;
			}
			.form-control, .btn {
			    min-height: 38px;
			    border-radius: 2px;
			}
			.btn {        
			    font-size: 15px;
			    font-weight: bold;
			}
			</style>
			
</head>
<body>
	<br>
	<div class="container">
	
		<div class="text-center text-warning">
			<c:if test="${param.error != null}">
				<span><i>Sorry ! you entered invalid Username or Password.</i></span>
			</c:if>
		</div>
		
		<div class="text-center text-success">
			<c:if test="${param.logout != null}">
				<span><i>you are logged out</i></span>
			</c:if>
		</div>
		
		<div class="login-form">
		    <form:form action="${pageContext.request.contextPath}/authenticatedTheUser" method="POST">
		        <h2 class="text-center">Log In</h2>       
		        <div class="form-group">
		        	<span class="form-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		            <input type="text" class="form-control" placeholder="Username" name="username" required="required">
		        </div>
		        <div class="form-group">
		        	<span class="form-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
		            <input type="password" class="form-control" placeholder="Password" name="password" required="required">
		        </div>
		        <div class="form-group">
		            <button type="submit" class="btn btn-primary btn-block">Log in</button>
		        </div>
		        <div class="clearfix">
		            <label class="float-left form-check-label"><input type="checkbox"> Remember me</label>
		            <a href="#" class="float-right">Forgot Password?</a>
		        </div>        
		    </form:form>
		    <p class="text-center"><a href="${pageContext.request.contextPath}/showRegistrationForm">Create an Account</a></p>
		</div>
	</div>
</body>
</html>