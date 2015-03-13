<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>.: Role apply :.</title>
        <spring:url value="/role/apply" var="role_apply_url" />
	</head>
	<body>
		<form id="formRoleApply" action="${role_apply_url}" method="post">
			<input type="hidden" id="subject" name="subject" value="${subject}"/>
			<input type="hidden" id="subjectId" name="subjectId" value="${subjectId}"/>
			<br/>Roles:
			<c:forEach var="role" items="${roles}">
				<br/>
				<c:if test="${!role.chosen}">
					<input type="checkbox" name="roleIDs" value="${role.id}"/>
				</c:if>
				<c:if test="${role.chosen}">
					<input type="checkbox" name="roleIDs" value="${role.id}" checked="checked"/>
				</c:if>
				${role.name} 
			</c:forEach>
			<br/><input type="button" value="Apply"/>
		</form>
	</body>
</html>