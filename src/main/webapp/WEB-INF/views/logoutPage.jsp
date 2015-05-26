<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<title>Logout Page</title>


<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div
			style="margin-left: 80px; margin-bottom: 40px; margin-right: 80px;">
			<h2 align="center">Wylogowano pomyślnie.</h2>
			<br>
			</div>
			<div class="control-group">
			<div class="controls">
			<form action="${pageContext.request.contextPath}/loginPage">
    			<input class="btn btn-primary" type="submit" value="Zaloguj się ponownie" style="display: block; margin: 0 auto;">
			</form>
				</div>
					</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>



						