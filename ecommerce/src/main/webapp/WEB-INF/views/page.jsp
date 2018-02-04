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
<meta name="description" content="Ecommerce">
<meta name="author" content="Prince">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

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

<!-- DataTable -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">

<!-- Custom styles for this template -->
<link href="${css}/style.css" rel="stylesheet">
<link href="${css}/carousalStyle.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">
		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->
		<div class="content">
			<c:if test="${userClickHome == true}">
				<%@include file="./home.jsp"%></c:if>
			<c:if test="${userClickAbout == true}">
				<%@include file="./about.jsp"%></c:if>
			<c:if test="${userClickContact == true}">
				<%@include file="./contact.jsp"%></c:if>
			<c:if
				test="${userClickAllProducts == true or userClickCategoryProducts == true}">
				<%@include file="./listProducts.jsp"%></c:if>
			<c:if test="${ userClickShowProduct == true}">
				<%@include file="./singleProduct.jsp"%></c:if>
			<c:if test="${ userClickManageProducts == true}">
				<%@include file="./manageProducts.jsp"%></c:if>
			<c:if test="${ userClickShowCart == true}">
				<%@include file="./cart.jsp"%></c:if>
		</div>
		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Jquery -->
		<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
		<!-- data table -->
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
		<script type="text/javascript"
			src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>

		<!-- bootstrap -->
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

		<!-- bootbox -->
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
		<script src="${js}/script.js"></script>
	</div>
</body>

</html>
