<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<div class="row">
		<c:if test="${message eq 'Product Submitted Successfully!'}">
			<div class="col-xs-12 col-md-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>
		</c:if>
		<c:if test="${message eq 'Category added Successfully!'}">
			<div class="col-xs-12 col-md-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>
		</c:if>
		<c:if test="${message eq 'Product Deleted Successfully!'}">
			<div class="col-xs-12 col-md-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>
		</c:if>
		<c:if test="${message eq 'Validation failed for product Submission!'}">
			<div class="col-xs-12 col-md-12">
				<div class="alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>
		</c:if>
		<div class="offset-md-2 col-md-8">
			<div class="card">
				<div class="card-header bg-primary text-white">
					<h4>Product Management</h4>
				</div>

				<div class="card-block">
					<br>
					<sf:form modelAttribute="product" enctype="multipart/form-data"
						action="${contextRoot}/manage/products" method="POST">
						<div class="form-group row">
							<label class="col-form-label col-md-4" for="name"> Enter
								Product Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name"
									placeholder="Product Name" class="form-control" />
								<sf:errors path="name" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-form-label col-md-4" for="brand"> Enter
								Brand Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand"
									placeholder="Brand Name" class="form-control" />
								<sf:errors path="brand" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-form-label col-md-4" for="description">
								Product Description: </label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4"
									class="form-control" placeholder="Write a description"></sf:textarea>
								<sf:errors path="description" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-form-label col-md-4" for="unitPrice">
								Enter Unit Price: </label>
							<div class="col-md-8">
								<sf:input type="text" path="unitPrice" id="unitPrice"
									placeholder="Unit Price" class="form-control" />
								<sf:errors path="unitPrice" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-form-label col-md-4" for="quantity">
								Quantity Available: </label>
							<div class="col-md-8">
								<sf:input type="text" path="quantity" id="quantity"
									placeholder="Quantity Available" class="form-control" />
								<sf:errors path="quantity" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-form-label col-md-4" for="file"> Select
								an Image: </label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-form-label col-md-4" for="categoryId">
								Select Category: </label>
							<div class="col-md-8">
								<sf:select path="categoryId" id="categoryId"
									class="form-control" itemLabel="name" itemValue="id"
									items="${categories}">

								</sf:select>
								<sf:errors path="categoryId" cssClass="help-block" element="em"></sf:errors>
								<c:if test="${product.id == 0 }">
									<div class="text-right">
										<br>
										<button type="button" data-toggle="modal"
											data-target="#categoryModal" class="btn btn-warning btn-sm" id="addCategoryButton">
											<i class="fa fa-plus"></i> &#160; Add Category
										</button>
									</div>
								</c:if>
							</div>
						</div>
						<sf:hidden path="id" />
						<sf:hidden path="code" />
						<sf:hidden path="supplierId" />
						<sf:hidden path="active" />
						<sf:hidden path="purchases" />
						<sf:hidden path="views" />
						<div class="form-group row">
							<div class="offset-md-4 col-md-8">
								<sf:button type="submit" name="submit" id="submit"
									class="btn btn-primary">Submit</sf:button>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>

	<!-- data table -->
	<div class="row">
		<div class="col-md-12">
			<h3>AVAILABLE PRODUCTS</h3>
		</div>

		<div class="col-md-12">
			<div style="overflow: auto;">
				<table id="adminProductsTable"
					class="table table-striped table-bordered">

					<thead>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>
						</tr>
					</tfoot>

				</table>
			</div>
		</div>
	</div>

	<div class="modal fade" id="categoryModal" role="dialog" tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Add new Category</h4>
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>

				<div class="modal-body">

					<!-- category form -->
					<sf:form modelAttribute="category" id="categoryForm" action="${contextRoot}/manage/category" method="POST" class="form-horizontal">
						<div class="form-group">
							<label for="category_name" class="control-label col-md-4">Category Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="category_name" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label for="category_description" class="control-label col-md-4">Category Description</label>
							<div class="col-md-8">
								<sf:textarea cols="4" rows="5" path="description" id="category_description" class="form-control"></sf:textarea>
							</div>
						</div>
						<div class="form-group">
							
							<div class="offset-md-4 col-md-8">
								<sf:button type="submit" path="name" class="btn btn-primary"><i class="fa fa-plus"></i> &#160;Add Category</sf:button>
							</div>
						</div>
					</sf:form>
				</div>

			</div>

		</div>
	</div>
</div>