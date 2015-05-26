<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<title>Rejestracja powiodła się</title>
		</head>
		<body>
		<div class="panel panel-primary">
			<h3 align="center">Dziękujemy za zarejestrowanie! Twoje dane:</h3>
			<p class="text-success" align="center"><b>Nazwa użytkownika: ${userForm.username}</b></p>
			<p class="text-success" align="center"><b>Adres e-mail: ${userForm.email}</b></p>
			</div>
		</body>
		</html>
	</tiles:putAttribute>
</tiles:insertDefinition>