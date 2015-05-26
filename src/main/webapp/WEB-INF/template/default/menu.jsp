<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<style type="text/css">
.menu {
	padding: 50px 10px 0px 10px;
	width: 200px;
	float: left;
	background-color: rgba(255, 255, 255, 0.9);
	margin-right: 30px;
	border-radius: 10px;
}
</style>

<div class="menu">
	<h4>Menu</h4>
	<ul class="nav nav-pills nav-stacked">
		<li><spring:url value="/home" var="homeUrl" htmlEscape="true" />
			<a href="${homeUrl}">Home</a></li>
		<li><spring:url value="/userprofile" var="userprofileUrl"
				htmlEscape="true" /> <a href="${userprofileUrl}">Profil</a></li>
		<li><spring:url value="/adminuserspage=1" var="adminusersUrl"
				htmlEscape="true" /> <a href="${adminusersUrl}">Użytkownicy</a></li>
	</ul>
</div>