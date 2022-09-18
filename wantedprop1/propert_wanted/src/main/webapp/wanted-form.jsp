<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Wanted</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Wanted Property </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Wanted</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${wanted != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${wanted == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${wanted != null}">
            			Edit Wanted Property
            		</c:if>
						<c:if test="${wanted == null}">
            			Add New Wanted Property
            		</c:if>
					</h2>
				</caption>

				<c:if test="${wanted != null}">
					<input type="hidden" name="id" value="<c:out value='${wanted.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Type</label> <input type="text"
						value="<c:out value='${wanted.type}' />" class="form-control"
						name="type" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Description</label> <input type="text"
						value="<c:out value='${wanted.description}' />" class="form-control"
						name="description">
				</fieldset>

				<fieldset class="form-group">
					<label>Budget</label> <input type="text"
						value="<c:out value='${wanted.price}' />" class="form-control"
						name="price">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Contact Name</label> <input type="text"
						value="<c:out value='${wanted.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Contact Number</label> <input type="text"
						value="<c:out value='${wanted.phone}' />" class="form-control"
						name="phone" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Location</label> <input type="text"
						value="<c:out value='${wanted.location}' />" class="form-control"
						name="location" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>