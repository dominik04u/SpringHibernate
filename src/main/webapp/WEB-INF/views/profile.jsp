<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div
			style="margin-left: 10px; margin-bottom: 0px; margin-right: 0px;">

			


			<hr style="background-color: black; height: 1px; border: 0;" />
			<div style="width: 40%; float: left; padding-right: 50px;">
				<h3>Dodaj książkę:</h3>
				<form:form action="addBook" method="post" commandName="bookForm"
					cssClass="form-horizontal">
					<div class="control-group">
						<!-- Username -->
						<label class="control-label">Tytuł</label>
						<div class="controls">
							<form:input path="title" cssClass="form-control" />
							<p>
								<form:errors path="title" cssClass="text-danger" />
							</p>
						</div>
					</div>

					<div class="control-group">
						<!-- E-mail -->
						<label class="control-label">Autor</label>
						<div class="controls">
							<form:input path="author" cssClass="form-control" />
							<p>
								<form:errors path="author" cssClass="text-danger" />
							</p>
						</div>
					</div>

					<div class="control-group">
						<!-- Password-->
						<label class="control-label">Rok wydania</label>
						<div class="controls">
							<form:input path="year" cssClass="form-control" />
						</div>
						<p>
							<form:errors path="year" cssClass="text-danger" />
						</p>
					</div>

					<div class="control-group">
						<div class="controls">
							<button class="btn btn-primary">Dodaj</button>
						</div>
					</div>
				</form:form>
			</div>
			<div style="float: left;">
				<h3>Moje książki:</h3>
				<c:if test="${!empty listBooks}">
					<table class="table table-striped table-hover ">
						<tr>
							<th width="130">Tytuł</th>
							<th width="130">Autor</th>
							<th width="100">Rok wydania</th>
							<th width="60">Usuń</th>
						</tr>
						<c:forEach items="${listBooks}" var="book">
							<tr>
								<td>${book.title}</td>
								<td>${book.author}</td>
								<td>${book.year}</td>
								<td><a href="<c:url value='/userRemoveBook/${book.id}'/> "><img alt="Delete" src="resources/images/delete.png" height="15" width="15"></a></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>