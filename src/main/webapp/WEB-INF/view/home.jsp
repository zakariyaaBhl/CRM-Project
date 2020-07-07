<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Home Page</title>
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
	              	
	         <security:authorize access="hasRole('ADMIN')">
	              <li><a href="${pageContext.request.contextPath}/addForm">AddProduct</a></li>
	         </security:authorize>
	              
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
	              <li class="active">
	              		<form:form method="POST" action="${pageContext.request.contextPath}/logout">
	              			<input type="submit" value="Sign Out" class="btn btn-success" style="margin-top: 8px;"><span class="sr-only">(current)</span>
	              		</form:form>
	              </li>
	            </ul>
	          </div><!--/.nav-collapse -->
	        </div><!--/.container-fluid -->
	      </nav>
	
	
	
	
	
	
	
	
	
	
	
	
		<div class="panel panel-info">
			<div class="panel-heading text-center"><h3>Products</h3></div>
			<div class="panel-body">
				<table class="table">
					<tr>
						<th class="text-center">ID</th>
						<th class="text-center">Désignation</th>
						<th class="text-center">Prix</th>
						<th class="text-center">Quantité</th>
						<th class="text-center">						</th>
					</tr>
					
						<c:forEach items="${listProduits}" var="p">
							<tr>
								<td class="text-center">${p.id}</td>
								<td class="text-center">${p.designation}</td>
								<td class="text-center">${p.prix}</td>
								<td class="text-center">${p.quantite}</td>
							
							<security:authorize access="hasRole('ADMIN')">
								<td class="text-center">
									<a href="${pageContext.request.contextPath}/updateForm?id=${p.id}" class="btn btn-default btn-block" type="submit">Update</a>
								</td>
								<td class="text-center">
									<a href="${pageContext.request.contextPath}/delete?id=${p.id}" class="btn btn-info btn-block" type="submit" 
									onclick=" confirm('Are you sure !!')"
									>Delete</a>
								</td>
							</security:authorize>
							</tr>
						</c:forEach>
						
					
				</table>
				
			</div>
		</div>
	</div>
</body>
</html>