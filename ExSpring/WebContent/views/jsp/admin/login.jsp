<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form name='loginForm' action="<c:url value='/admin/j_spring_security_check'/>" method='POST'>
    <input type="hidden" name="formTitle" value="Login"/>
	<table>
		<tr>
		    <td>User:</td>
		    <td><input type='text' name='j_username'/></td>
		</tr>
		<tr>
		    <td>Password:</td>
		    <td><input type='password' name='j_password' /></td>
		</tr>
		<tr>
		    <td colspan='2'><input name="submit" type="button" value="Login" /></td>
		</tr>
	</table>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<script type="text/javascript">
	$(function() {
		$('input[value="Login"]').click(function() {
		    var form = $('form[name="loginForm"]');    
		    ajax(form.attr('action'), function(result) {
		        if (result.STATUS) {
		        	$(document).trigger("LOGIN_SUCCESS");
		        } else {
		        	alert("Login fail!");
		        }
		    }, form.attr('method'), form.serialize());
		});
	});
</script>