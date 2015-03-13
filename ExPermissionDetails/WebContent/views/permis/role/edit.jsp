<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>.: Role edit :.</title>
        <spring:url value="/role/edit" var="role_edit_url" />
	</head>
	<body>
		<form id="formRoleEdit" action="${role_edit_url}" method="post">
			<input type="hidden" id="roleId" name="roleId" value=""/>
			Role name:
			<input id="name" name="name"/><br/>
			<br/>Permissions:
			<c:forEach var="permis" items="${permissions}">
				<br/>
				<input type="checkbox" name="permisIDs" value="${permis.id}"/>
				${permis.name} 
			</c:forEach>
			<t:canAccess permis="ROLE_WRITE">
				<br/><input type="button" value="Save"/>
			</t:canAccess>
		</form>
	</body>
</html>