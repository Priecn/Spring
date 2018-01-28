<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp"%>
<div class="container">
	<div class="offset-md-3 col-md-6">
		<div class="card">
			<div class="card-header bg-primary text-white">
				<h4>Sign Up - Address</h4>
			</div>

			<div class="card-block">
				<br>
				<sf:form id="billingForm" method="POST"
					modelAttribute="billingAddress">
					<div class="form-group row">
						<label class="col-form-label col-md-4" for="addressLineOne">
							Address Line One: </label>
						<div class="col-md-8">
							<sf:input type="text" path="addressLineOne" id="addressLineOne"
								placeholder="Address Line One" class="form-control" />
							<sf:errors path="addressLineOne" cssClass="help-block"
								element="em"></sf:errors>
						</div>
					</div>

					<div class="form-group row">
						<label class="col-form-label col-md-4" for="addressLineTwo">
							Address Line Two: </label>
						<div class="col-md-8">
							<sf:input type="text" path="addressLineTwo" id="addressLineTwo"
								placeholder="Address Line Two" class="form-control" />
							<sf:errors path="addressLineTwo" cssClass="help-block"
								element="em"></sf:errors>
						</div>
					</div>

					<div class="form-group row">
						<label class="col-form-label col-md-4" for="city"> City: </label>
						<div class="col-md-8">
							<sf:input type="text" path="city" id="city" placeholder="City"
								class="form-control" />
							<sf:errors path="city" cssClass="help-block" element="em"></sf:errors>
						</div>
					</div>

					<div class="form-group row">
						<label class="col-form-label col-md-4" for="postalCode">
							Postal Code: </label>
						<div class="col-md-8">
							<sf:input type="text" path="postalCode" id="postalCode"
								placeholder="XXXXXX" class="form-control" />
							<sf:errors path="postalCode" cssClass="help-block" element="em"></sf:errors>
						</div>
					</div>

					<div class="form-group row">
						<label class="col-form-label col-md-4" for="state"> State:
						</label>
						<div class="col-md-8">
							<sf:input type="text" path="state" id="state" placeholder="State"
								class="form-control" />
							<sf:errors path="state" cssClass="help-block" element="em"></sf:errors>
						</div>
					</div>

					<div class="form-group row">
						<label class="col-form-label col-md-4" for="country">
							Country: </label>
						<div class="col-md-8">
							<sf:input type="text" path="country" id="country"
								placeholder="Country" class="form-control" />
							<sf:errors path="country" cssClass="help-block" element="em"></sf:errors>
						</div>
					</div>

					<div class="form-group row">
						<div class="offset-md-4 col-md-8">
							<sf:button type="submit" name="_eventId_personal" id="submit"
								class="btn btn-primary">
								<li class="fa fa-arrow-circle-left"></li> Previous-Personal</sf:button>
							<sf:button type="submit" name="_eventId_confirm" id="submit"
								class="btn btn-primary">Next-Confirm <li
									class="fa fa-arrow-circle-right"></li>
							</sf:button>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
	</div>
</div>


<%@include file="../shared/flows-footer.jsp"%>
