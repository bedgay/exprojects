<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>.: News :.</title>
	</head>
	<body>
		<table id="tblNews" subject="NEWS" class="display">
			<caption>News [ <a name="fnAddNew" href="#">Add news</a> | <a name="fnApplyRoles" href="#">Apply roles</a> ]</caption>
			<thead>
				<tr>
					<th></th>
					<th>Order</th>
					<th>Title</th>
					<th>Description</th>
				</tr>
			</thead>
			<c:forEach var="item" items="${news}" varStatus="status">
				<tbody>
					<tr>
						<td><input type="radio" name="newsId" value="${item.id}"/></td>
						<td>${status.index + 1}</td>
						<td>${item.title}</td>
						<td>${item.description}</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</body>
</html>