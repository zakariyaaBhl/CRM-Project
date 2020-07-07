<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Add Produit</title>
</head>
<body>
<br>
	<div class="container col-md-8 col-md-offset-2">
	
		 <!-- Static navbar -->
	      <nav class="navbar navbar-default">
	        <div class="container-fluid">
	          <div class="navbar-header">
	            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	              <span class="sr-only">Toggle navigation</span>
	              <span class="icon-bar"></span>
	              <span class="icon-bar"></span>
	              <span class="icon-bar"></span>
	            </button>
	            <a class="navbar-brand" href="${pageContext.request.contextPath}">Project name</a>
	          </div>
	          <div id="navbar" class="navbar-collapse collapse">
	            <ul class="nav navbar-nav">
	              <li class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
	              <li><a href="${pageContext.request.contextPath}/addForm">AddProduct</a></li>
	              <li><a href="#">About</a></li>
	              <li class="dropdown">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
	                <ul class="dropdown-menu">
	                  <li><a href="#">Action</a></li>
	                  <li><a href="#">Another action</a></li>
	                  <li><a href="#">Something else here</a></li>
	                  <li role="separator" class="divider"></li>
	                  <li class="dropdown-header">Nav header</li>
	                  <li><a href="#">Separated link</a></li>
	                  <li><a href="#">One more separated link</a></li>
	                </ul>
	              </li>
	            </ul>
	            <ul class="nav navbar-nav navbar-right">
	              <li class="active"><a href="./">Default <span class="sr-only">(current)</span></a></li>
	              <li><a href="../navbar-static-top/">Static top</a></li>
	              <li><a href="../navbar-fixed-top/">Fixed top</a></li>
	            </ul>
	          </div><!--/.nav-collapse -->
	        </div><!--/.container-fluid -->
	      </nav>
	
	
	
	
	
	
	
	
	
	
	
	
		<div class="panel panel-primary">
			<div class="panel-heading text-center"><h2>Add New Product</h2></div>
			<div class="panel-body">
				
				<form:form action="${pageContext.request.contextPath}/add" method="POST" modelAttribute="prod">
				  
				  <div class="form-group text-center">
				    <label for="designation">Désignation : </label>
				    <form:errors path="designation" cssClass="error text-danger" />
				    <input type="text" class="form-control text-center" id="designation" name="designation" value="${produit.designation}" required="required">
				  </div>
				  
				  <div class="form-group text-center">
				    <label for="prix">Prix : </label>
				    <form:errors path="prix" cssClass="error text-danger" />
				    <input type="text" class="form-control text-center" id="prix" name="prix" value="${produit.prix}">
				  </div>
				  
				  <div class="form-group text-center">
				    <label for="quantite">Quantité : </label>
				    <form:errors path="quantite" cssClass="error text-danger" />
				    <input type="text" class="form-control text-center" id="quantite" name="quantite" value="${produit.quantite}">
				  </div>
				  
				  <div class="form-group text-center">
					  <button type="submit" class="btn btn-success ">Add New Product</button>
					  <a type="submit" class="btn btn-default " href="${pageContext.request.contextPath}/">Cancel Operation</a>
				  </div>
				  
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>