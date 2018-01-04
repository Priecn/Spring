<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="css" value="/resources/css" />
<spring:url var="btcss" value="/resources/vendor/bootstrap/css" />
<spring:url var="btjs" value="/resources/vendor/bootstrap/js" />
<spring:url var="jquery" value="/resources/vendor/jquery" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shopping Cart - ${title}</title>

    <!-- Bootstrap core CSS -->
    <link href="${btcss}/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${css}/shop-homepage.css" rel="stylesheet">

  </head>

  <body>

    <!-- Navigation -->
    <%@include file="./shared/navbar.jsp" %> >
    
    <!-- Page Content -->
    <c:if test="${userClickHome == true}">
		<%@include file="./home.jsp" %>
	</c:if>
	
	<c:if test="${userClickAbout == true}">
		<%@include file="./about.jsp" %>
	</c:if>
	
	<c:if test="${userClickContact == true}">
		<%@include file="./contact.jsp" %>
	</c:if>
	
    <!-- Footer -->
    <%@include file="./shared/footer.jsp" %>
    
    <!-- Bootstrap core JavaScript -->
    <script src="${jquery}/jquery.min.js"></script>
    <script src="${btjs}/bootstrap.bundle.min.js"></script>

  </body>

</html>
