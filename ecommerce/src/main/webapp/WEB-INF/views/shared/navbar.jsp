<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${contextRoot}/home">Ecommerce</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item" id="home"><a class="nav-link"
					href="${contextRoot}/home">Home <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item" id="about"><a class="nav-link"
					href="${contextRoot}/about">About</a></li>
				<li class="nav-item" id="contact"><a class="nav-link"
					href="${contextRoot}/contact">Contact</a></li>
				<li class="nav-item" id="listProducts"><a class="nav-link"
					href="${contextRoot}/show/all/products">View Products</a></li>
				<security:authorize access="hasAuthority('ADMIN')">
					<li class="nav-item" id="manageProducts"><a class="nav-link"
						href="${contextRoot}/manage/products">Manage Products</a></li>
				</security:authorize>
			</ul>

			<ul class="nav navbar-nav ml-auto">
				<security:authorize access="isAnonymous()">
					<li class="nav-item" id="register"><a class="nav-link"
						href="${contextRoot}/register">Sign up</a></li>
					<li class="nav-item" id="login"><a class="nav-link"
						href="${contextRoot}/login">Login</a></li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="javascript:void(0)"
						data-toggle="dropdown" id="profileDropDown">
							${userModel.fullName} </a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="${contextRoot}/profile">Profile</a>
							<security:authorize access="hasAuthority('USER')">
								<a class="dropdown-item" href="${contextRoot}/cart"><span
									class="fa fa-shopping-cart"></span>&#160;${userModel.cart.cartLines}&#160;Items-
									&#8377; &#160;${userModel.cart.grandTotal} </a>
							</security:authorize>
							<a class="dropdown-item" href="${contextRoot}/signout">Logout</a>
						</div></li>
				</security:authorize>
			</ul>
		</div>
	</div>
</nav>

<script>
	window.userRole = '${userModel.role}';
</script>