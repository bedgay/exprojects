<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>.: User Roles :.</title>
        <spring:url value="/user/roles" var="user_roles_url" />
	</head>
	<body>
		<form id="formPermissions" action="${user_roles_url}" method="post">
			<input type="hidden" id="userId" name="userId" value="${user.id}"/> 
			<c:forEach var="role" items="${user.roleDTOs}">
				<c:if test="${role.chosen == true}">
					<input type="checkbox" name="roleIDs" value="${role.id}" checked="checked"/>
				</c:if>
				<c:if test="${role.chosen == false}">
					<input type="checkbox" name="roleIDs" value="${role.id}"/>
				</c:if>
				${role.name} <br/>
			</c:forEach>
			<t:canAccess permis="USER_WRITE" subject="USER" subjectId="${user.id}">
				<input type="button" value="Save"/>
			</t:canAccess>
		</form>
	</body>
</html>