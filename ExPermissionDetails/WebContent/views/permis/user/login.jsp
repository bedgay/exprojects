<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>.: Login :.</title>
        <spring:url value="/user/login" var="login_url" />
	</head>
	<body>
		<form id="formLogin" action="${login_url}" method="post">
			<table>
				<tr>
					<td>Username:</td>
					<td><input id="username" name="username" value="admin"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input id="password" name="password" type="password" value="admin"/></td>
				</tr>
				<tr>
					<td><input type="button" value="Login"/></td>
					<td></td>
				</tr>
			</table>
		</form>
	</body>
</html>