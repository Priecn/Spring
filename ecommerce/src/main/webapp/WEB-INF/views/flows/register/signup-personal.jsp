<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp"%>
<div class="container">
	<div class="row">

		<div class="offset-md-3 col-md-6">
			<div class="card">
				<div class="card-header bg-primary text-white">
					<h4>Sign Up - Personal</h4>
				</div>

				<div class="card-block">
					<br>
					<sf:form id="registerForm" method="POST" modelAttribute="user">
						<div class="form-group row">
							<label class="col-form-label col-md-4" for="firstName">
								First Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="firstName" id="firstName"
									placeholder="First Name" class="form-control" />
								<sf:errors path="firstName" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-form-label col-md-4" for="lastName">
								Last Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="lastName" id="lastName"
									placeholder="Last Name" class="form-control" />
								<sf:errors path="lastName" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-form-label col-md-4" for="email">
								Email: </label>
							<div class="col-md-8">
								<sf:input type="email" path="email" id="email"
									placeholder="Email" class="form-control" />
								<sf:errors path="email" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-form-label col-md-4" for="contactNumber">
								Contact Number: </label>
							<div class="col-md-8">
								<sf:input type="text" path="contactNumber" id="contactNumber"
									placeholder="XXXXXXXXXX" class="form-control" />
								<sf:errors path="contactNumber" cssClass="help-block"
									element="em"></sf:errors>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-form-label col-md-4" for="password">
								Password: </label>
							<div class="col-md-8">
								<sf:input type="password" path="password" id="password"
									placeholder="********" class="form-control" />
								<sf:errors path="password" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						
						<div class="form-group row">
							<label class="col-form-label col-md-4" for="confirmPassword">
								Confirm Password: </label>
							<div class="col-md-8">
								<sf:input type="password" path="confirmPassword" id="confirmPassword"
									placeholder="********" class="form-control" />
								<sf:errors path="confirmPassword" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>

						<!-- bootstrap radio button -->
						<div class="form-group row">
							<label class="col-form-label col-md-4" for="role"> Select
								Role: </label>
							<div class="col-md-8">
								<label class="radio-inline"> <sf:radiobutton path="role"
										value="USER" checked="checked" /> User
								</label> <label class="radio-inline"> <sf:radiobutton
										path="role" value="SUPPLIER" /> Supplier
								</label>
							</div>
						</div>

						<div class="form-group row">
							<div class="offset-md-4 col-md-8">
								<sf:button type="submit" name="_eventId_billing" id="submit"
									class="btn btn-primary">Next-Billing <li
										class="fa fa-arrow-circle-right"></li>
								</sf:button>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="../shared/flows-footer.jsp"%>
