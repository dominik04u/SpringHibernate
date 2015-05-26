<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<div class="header" style="margin-bottom: 10px;">
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom">
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<spring:url value="/home" var="homeUrl" htmlEscape="true" />
				<a class="navbar-brand" href="${homeUrl}"><img
					alt="Książki - moja pasja"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="active"><a href="${homeUrl}">Home</a></li>
					<spring:url value="/userprofile" var="userprofileUrl" htmlEscape="true"/>
					<li><a href="${userprofileUrl}">Profil</a></li>
					<security:authorize access="!isAuthenticated()">
						<spring:url value="/loginPage" var="loginUrl" htmlEscape="true" />
						<li><a class="btn" href="${loginUrl}">ZALOGUJ</a></li>
						<spring:url value="/register" var="registerUrl" htmlEscape="true" />
						<li><a class="btn" href="${registerUrl}">ZAREJESTRUJ</a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li><a href='<s:url value="/logout"></s:url>'>Wyloguj (<security:authentication
									property="principal.username" />)
						</a></li>
					</security:authorize>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<!-- /.navbar -->
</div>