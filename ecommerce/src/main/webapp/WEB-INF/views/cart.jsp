<link href="${css}/cart.css" rel="stylesheet">
<div class="container">
	<c:if test="${not empty message}">
		<div class="col-xs-12 col-md-12">
			<div class="alert alert-info">
				<h5 class="text-center">${message}</h5>
			</div>
		</div>
	</c:if>
	
	<c:choose>
		<c:when test="${not empty cartLines}">
			<table id="cart" class="table table-hover table-condensed">
				<thead>
					<tr>
						<th style="width: 50%">Product</th>
						<th style="width: 10%">Price</th>
						<th style="width: 8%">Quantity</th>
						<th style="width: 22%" class="text-center">Subtotal</th>
						<th style="width: 10%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cartLines}" var="cartLine">
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs">
										<img src="${images}/${cartLine.product.code}.jpg"
											alt="${cartLine.product.name}" class="img img-thumbnail float-left cartImg" />
									</div>
									<div class="col-sm-10">
										<a href="${contextRoot}/show/${cartLine.product.id}/product"><h4 class="nomargin">${cartLine.product.name}</h4></a>
										<c:if test="${cartLine.available == false}">
											<strong class="unavailable">(Not Available)</strong>
										</c:if>
										<p>Brand - ${cartLine.product.brand}</p>
										<p>Description - ${cartLine.product.description}</p>
									</div>
								</div>
							</td>
							<td data-th="Price">&#8377; &#160; ${cartLine.buyingPrice}</td>
							<td data-th="Quantity"><input type="number"
								id="count_${cartLine.id}" min="1" max="3"
								class="form-control text-center"
								value="${cartLine.productCount}" /></td>
							<td data-th="Subtotal" class="text-center">&#8377; &#160;
								${cartLine.total}</td>
							<td class="actions" data-th="">
								<button type="button" name="refreshCart" value="${cartLine.id}"
									class="btn btn-info btn-sm">
									<i class="fa fa-refresh"></i>
								</button>
								<a href="${contextRoot}/cart/${cartLine.id}/delete" class="btn btn-danger btn-sm">
									<i class="fa fa-trash-o"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<!-- <tr class="visible-xs">
						<td class="text-center"><strong>Total - &#8377; &#160; ${userModel.cart.grandTotal}</strong></td>
					</tr>  -->
					<tr>
						<td><a href="${contextRoot}/show/all/products" class="btn btn-warning"><i
								class="fa fa-angle-left"></i> Continue Shopping</a></td>
						<td colspan="2" class="hidden-xs"></td>
						<td class="hidden-xs text-center"><strong>Total -
								&#8377; &#160; ${userModel.cart.grandTotal}</strong></td>
						<td><a href="#" class="btn btn-success btn-block">Checkout
								<i class="fa fa-angle-right"></i>
						</a></td>
					</tr>
				</tfoot>
			</table>
		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col-md-12">
					<div class="jumbotron jumbotron-fluid text-center">
						<i class="fa fa-shopping-cart fa-5x" style="color: #8c8c8c;"></i>
						<h1 class="display-3">Your cart is empty</h1>
						<hr class="my-4">
						<p class="lead">
							<a href="${contextRoot}/home" class="btn btn-primary">
								Continue Shopping </a>
						</p>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>