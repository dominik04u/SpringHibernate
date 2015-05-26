<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<title>Rejestracja</title>
</head>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div style="margin-left: 80px; margin-bottom: 40px; max-width: 450px">
			<div class="form-group">
				<h3>Zarejestruj</h3>
				<form:form action="register" method="post" commandName="userForm"
					cssClass="form-horizontal">
					<div class="control-group">
						<!-- Username -->
						<label class="control-label">Username</label>
						<div class="controls">
							<form:input path="username" cssClass="form-control" />
							<p>
								<form:errors path="username" cssClass="text-danger" />
							</p>
						</div>
					</div>

					<div class="control-group">
						<!-- E-mail -->
						<label class="control-label">Password</label>
						<div class="controls">
							<form:password path="password" cssClass="form-control" />
							<p>
								<form:errors path="password" cssClass="text-danger" />
							</p>
						</div>
					</div>

					<div class="control-group">
						<!-- Password-->
						<label class="control-label">Email</label>
						<div class="controls">
							<form:input path="email" cssClass="form-control" />
						</div>
						<p>
							<form:errors path="email" cssClass="text-danger" />
						</p>
					</div>

					<div class="control-group">
						<!-- Button -->
						<div class="controls">
							<button class="btn btn-primary">Zarejestruj</button>
						</div>
					</div>
				</form:form>
				<p class="text-danger">${notification}</p>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>