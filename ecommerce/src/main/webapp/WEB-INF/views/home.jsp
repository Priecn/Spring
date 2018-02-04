
<div class="container">

	<div class="row">

		<div class="col-lg-3">

			<%@include file="./shared/sidebar.jsp"%>

		</div>
		<!-- /.col-lg-3 -->

		<div class="col-lg-9">

			<div id="latestOffers" class="carousel slide my-4"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleIndicators" data-slide-to="0"
						class="active"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<img class="d-block img-fluid" src="http://placehold.it/900x350"
							alt="First slide">
					</div>
					<div class="carousel-item">
						<img class="d-block img-fluid" src="http://placehold.it/900x350"
							alt="Second slide">
					</div>
					<div class="carousel-item">
						<img class="d-block img-fluid" src="http://placehold.it/900x350"
							alt="Third slide">
					</div>
				</div>
				<a class="left slide-control banners" href="#latestOffers"
					role="button" data-slide="prev"><i class="fa fa-angle-left"></i>
				</a> <a class="right slide-control banners" href="#latestOffers"
					role="button" data-slide="next"> <i class="fa fa-angle-right"></i>
				</a>
			</div>
			<c:forEach items="${categories}" var="category">
				<c:if test="${not empty mostRecentProducts[category.id]}">
					<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
						<div class="container">
							<a class="navbar-brand"
								href="${contextRoot}/show/category/${category.id}/products">${category.name}</a>
						</div>
					</nav>
					<div id="latest${category.name}" class="carousel slide"
						data-ride="carousel">
						<div class="carousel-inner" role="listbox">
							<div class="carousel-item active">
								<div class="row">
									<c:forEach items="${mostRecentProducts[category.id]}"
										var="product">
										<div class="col-lg-4 col-md-6 mb-4">
											<div class="card h-100">
												<a href="#"><img class="card-img-top"
													src="${images}/${product.code}.jpg" alt=""></a>
												<div class="card-body">
													<h4 class="card-title">
														<a href="${contextRoot}/show/${product.id}/product">${product.name}</a>
													</h4>
													<h5>&#8377; &#160; ${product.unitPrice}</h5>
													<p class="card-text">${product.description}</p>
												</div>
												<div class="card-footer">
													<small class="text-muted">&#9733; &#9733; &#9733;
														&#9733; &#9734;</small>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<c:if test="${not empty nextRecentProducts[category.id]}">
								<div class="carousel-item">
									<div class="row">
										<c:forEach items="${nextRecentProducts[category.id]}" var="product">
											<div class="col-lg-4 col-md-6 mb-4">
												<div class="card h-100">
													<a href="#"><img class="card-img-top"
														src="${images}/${product.code}.jpg" alt=""></a>
													<div class="card-body">
														<h4 class="card-title">
															<a href="${contextRoot}/show/${product.id}/product">${product.name}</a>
														</h4>
														<h5>&#8377; &#160; ${product.unitPrice}</h5>
														<p class="card-text">${product.description}</p>
													</div>
													<div class="card-footer">
														<small class="text-muted">&#9733; &#9733; &#9733;
															&#9733; &#9734;</small>
													</div>
												</div>
											</div>
										</c:forEach>
										<!-- /.row -->
									</div>
								</div>
							</c:if>
						</div>
						<a class="left slide-control" href="#latest${category.name}"
							role="button" data-slide="prev"><i class="fa fa-angle-left"></i>
						</a> <a class="right slide-control" href="#latest${category.name}"
							role="button" data-slide="next"> <i class="fa fa-angle-right"></i>
						</a>
					</div>
				</c:if>
			</c:forEach>
		</div>
		<!-- /.col-lg-9 -->
	</div>
	<!-- /.row -->
</div>
<!-- container -->