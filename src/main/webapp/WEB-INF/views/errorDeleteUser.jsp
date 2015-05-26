<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">


		<div
			style="margin-left: 100px; margin-bottom: 50px; margin-right: 100px;">
			<h1 align="center">Błąd</h1>

			<p align="center">Nie możesz dodać tej książki. Posiadasz już taką w swojej bazie</p>
		</div>


	</tiles:putAttribute>
</tiles:insertDefinition>