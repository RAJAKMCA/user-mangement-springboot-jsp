<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="panel panel-primary">
				<div class="panel-heading">Add New User</div>
				<div class="panel-body">
								
					<form:form method="post" modelAttribute="addUser">
						<form:hidden path="id" />
						<fieldset class="form-group">
							<form:label path="name">user name</form:label>
							<form:input path="name" type="text" class="form-control"
								required="required" />
							<form:errors path="name" cssClass="text-warning" />
						</fieldset>
						
						<fieldset class="form-group">
							<form:label path="address"> Address</form:label>
							<form:input path="address" type="text" class="form-control"
								required="required" />
							<form:errors path="address" cssClass="text-warning" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="dateOfBirth">Date Of Birth</form:label>
							<form:input path="dateOfBirth" type="text" class="form-control"
								required="required" />
							<form:errors path="dateOfBirth" cssClass="text-warning" />
						</fieldset>
						
						<fieldset class="form-group">
							<form:label path="zone"> ZOne Location</form:label>
							<form:input path="zone" type="text" class="form-control"
								required="required" />
							<form:errors path="zone" cssClass="text-warning" />
						</fieldset>

						<button type="submit" class="btn btn-success">Save</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>