<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<s:url var="css" value="/resources/css" />
<s:url var="js" value="/resources/js" />
<s:url var="images" value="/resources/images" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Ecommerce - ${title}</title>
<script type="text/javascript">
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- font awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<!-- Theme -->
<link href="${css}/bootstrap-yeti-theme.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">

<!-- Custom styles for this template -->
<link href="${css}/style.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
			<div class="container">
				<a class="navbar-brand" href="${contextRoot}/home">Ecommerce</a>
			</div>
		</nav>
		<!-- <br> <br><br> -->
		<!-- Page Content -->
		<div class="content">
			<div class="container">
				<!-- wrong credential message -->
				<c:if test="${not empty message}">
					<div class="row">
						<div class="offset-md-2 col-md-8">
							<div class="alert alert-danger">${message}</div>
						</div>
					</div>
				</c:if>
				<!-- logout message -->
				<c:if test="${not empty logout}">
					<div class="row">
						<div class="offset-md-2 col-md-8">
							<div class="alert alert-success">${logout}</div>
						</div>
					</div>
				</c:if>
				<div class="row">

					<div class="offset-md-2 col-md-8">
						<div class="card">
							<div class="card-header bg-primary text-white">
								<h4>Login</h4>
							</div>

							<div class="card-block">
								<br>
								<form action="${contextRoot}/login" method="POST" id="loginForm">
									<div class="form-group row">
										<label class="col-form-label col-md-4" for="username">
											Enter Email: </label>
										<div class="col-md-8">
											<input type="text" name="username" id="username"
												placeholder="Email" class="form-control" />
										</div>
									</div>

									<div class="form-group row">
										<label class="col-form-label col-md-4" for="password">
											Password: </label>
										<div class="col-md-8">
											<input type="password" name="password" id="password"
												placeholder="*******" class="form-control" />
										</div>
									</div>

									<div class="form-group row">
										<div class="offset-md-4 col-md-8">
											<button type="submit" name="submit" id="submit"
												class="btn btn-primary">Login</button>
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
										</div>
									</div>
								</form>
							</div>
							<div class="card-footer text-muted text-right">
								New User? - <a href="${contextRoot}/register">Sign Up</a>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- <br><br> -->
			<!-- Footer -->
			<%@include file="./shared/footer.jsp"%>
		</div>
		<!-- Jquery -->
		<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
		<!-- bootstrap -->
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
		<script src="${js}/script.js"></script>
	</div>
</body>

</html>
