<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page contentType="text/html; charset=UTF-8"%>
<title>Lista użytkowników</title>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<h1>Edycja użytkownika:</h1>
		<c:if test="${!empty user.username}">

			<form:form action="adminUpdateUser" commandName="user">
				<table>
					<c:if test="${!empty user.username}">
						<tr>
							<td><form:label class="control-label"  path="id">
									<spring:message text="ID" />
								</form:label></td>
							<td><form:input class="form-control input-sm" path="id" readonly="true" size="10"
									disabled="true" /> <form:hidden path="id" /></td>
						</tr>
					</c:if>
					<tr>
						<td><form:label class="control-label"  path="username">
								<spring:message text="Login" />
							</form:label></td>
						<td><form:input class="form-control input-sm" path="username" /></td>
					</tr>
					<tr>
						<td><form:label class="control-label" path="email">
								<spring:message text="Email" />
							</form:label></td>
						<td><form:input class="form-control input-sm" path="email" /></td>
					</tr>
					<tr>
						<td colspan="2"><input class="btn btn-primary" type="submit"
							value="<spring:message text="Zatwierdź"/>" /></td>
					</tr>
				</table>
			</form:form>
		</c:if>

		<h3>Użytkownicy:</h3>
		<c:if test="${!empty listUsers}">
			<table class="table table-striped table-hover">
				<tr>
					<th width="80">User ID</th>
					<th width="120">Username</th>
					<th width="120">Email</th>
					<th width="60">Edit</th>
					<th width="60">Delete</th>
				</tr>
				<c:forEach items="${listUsers}" var="u">
					<tr>
						<td>${u.id}</td>
						<td>${u.username}</td>
						<td>${u.email}</td>
						<td><a
							href="<c:url value='/adminEditUser${u.id}page${pageNumber}' />">Edit</a></td>
						<td><a href="<c:url value='/adminRemoveUser/${u.id}' />">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<ul class="pager">
				<li>
					<c:if test="${pageNumber > 1 }">
						<td style="padding-right: 15px;"><a
							href="<c:url value='/adminuserspage=${pageNumber - 1}' />">Back</a></td>
					</c:if>
					<li style="padding-right: 15px;">
						<p>${pageNumber }</p>
					</li>
					<c:if test="${hasNextPage}">
						<li><a
							href="<c:url value='/adminuserspage=${pageNumber + 1}' />">Next</a>
						</li>
					</c:if>
				</li>
			</ul>
		</c:if>
	</tiles:putAttribute>
</tiles:insertDefinition>


