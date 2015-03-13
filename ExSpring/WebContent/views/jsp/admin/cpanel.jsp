<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>.: cPanel :.</title>
		
        <spring:url value="/resources/js" var="js_url" />
        <spring:url value="/resources/jquery-ui" var="ui_url" />
        <spring:url value="/resources/css" var="css_url" />
        
        <spring:url value="/admin/login" var="login_url" />
        <spring:url value="/j_spring_security_logout" var="logout_url" />
        
        <spring:url value="/admin/users" var="users_url" />
        <spring:url value="/admin/addUser" var="add_user_url" />
        
        <link href="${css_url}/style.css" rel="stylesheet" type="text/css"/>
        <link href="${ui_url}/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        
        <script type="text/javascript" src="${js_url}/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="${ui_url}/jquery-ui.min.js"></script>
        <script type="text/javascript">
        var func = null;
        $(function() {
        	$(document).bind("LOGIN_SUCCESS", function() {
        		if (func != null) {
        			$('#dialog').dialog('close');
                    eval(func);
        			func = null;
        		}
        	});
        	loadUsers();
        	
        	$('#funcAddUser').click(function() {
        		$('#editDialog').dialog();
        	});
        	$('#editDialog input[value="Save"]').click(function() {
        		alert("${add_user_url}");
        		
                $.ajax({
                    url: '${add_user_url}',
                    type: 'post',
                    data: $('#editDialog form').serialize(),
                    success: function(result) { 
                    	alert(result);
                        //TODO: debug JSON
                        //alert(JSON.stringify(result));  
                        /* if (result.STATUS == undefined) {
                            $('#dialog').html(result).attr("title", $('input[name="formTitle"]').val()).dialog();
                        } else {
                            if (callback != undefined) {
                                callback(result);
                            }
                        } */
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        alert(thrownError);
                    }
                });
        	});
        });
        
        function loadUsers() {
        	func = "loadUsers();";
        	ajax('${users_url}', function(result) {
                var usersBody = $('#users .body');
                if (result.STATUS) {
                	usersBody.empty();
                	var groups = new Array();
                    $.each(result.DATA, function(index, user) {
                    	var group = null;
                    	if (user.group.name == undefined) {
                    		group = groups[user.group];
                    	} else {
                    		groups[user.group["@id"]] = user.group;
                    		group = user.group;
                    	}
                    	
                    	usersBody.append("<tr><td>" + (index + 1) + "</td><td>" + 
                                user.username + "</td><td>" + 
                    			user.profile.firstName + " " + 
                    			user.profile.lastName + "</td><td>" + 
                    			user.profile.phone + "</td><td>" + 
                                user.profile.email + "</td><td>"+
                                group.name + "</td></tr>");
                    });
                } else {
                	usersBody.empty().append("<tr><td colspan='6'>Empty</td></tr>");
                }
            }, 'GET', {});
        }
        
        function ajax(url, callback, type, data) {
        	if (type == undefined) type = 'GET';
        	if (data == undefined) data = {};
            $.ajax({
                url: url,
                type: type,
                data: data,
                success: function(result) { 
                	//TODO: debug JSON
                	//alert(JSON.stringify(result));  
                    if (result.STATUS == undefined) {
                        $('#dialog').html(result).attr("title", $('input[name="formTitle"]').val()).dialog();
                    } else {
                    	if (callback != undefined) {
                            callback(result);
                    	}
                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(thrownError);
                }
            });
        }
        </script>
	</head>
	<body>
        <table><tr>
            <td>
                <a id="funcLoadUsers" href="javascript:loadUsers();">Users</a>
                |<a id="funcAddUser" href="javascript:addUser();">Add User</a>
            </td>
	        <s:authorize access="hasRole('ROLE_USER')">
	            <td>
	                <form action="${logout_url}" method="post" id="logoutForm">
	                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	                </form>
	                <a href="javascript:logout()">Logout</a>
	            </td>
	            <script type="text/javascript">
	                function logout() {
	                	var form = $('#logoutForm');
	                	ajax(form.attr('action'), function(result) {
	                		location.reload();
	                	}, form.attr('method'), form.serialize());
	                }
	            </script>
	        </s:authorize>
        </tr></table>
        <table id="users" class="table" cellspacing="1">
            <tr class="header">
                <td>Order</td><td>Username</td><td>Full name</td><td>Phone</td><td>Email</td><td>Group</td>
            </tr>
            <tbody class="body"><tr><td colspan='6'>Empty</td></tr></tbody>
        </table>
		
        <div id="dialog" title="" style="display: none;">
        </div>
        
        <div id="editDialog" title="Edit dialog" style="display: none;">
            <form action="${add_user_url}" method="post">
                <b>Msg</b><br/>
                Username: <input name="username"/><br/> 
                Password: <input name="password"/><br/>
                Confirm password: <input name="password"/><br/>
                <input name="active" value="1" type="hidden"/>
                
                First name: <input name="profile.firstName"/><br/>
                Last name: <input name="profile.lastName"/><br/>
                Phone: <input name="profile.phone"/><br/>
                Email: <input name="profile.email"/><br/>
                
                <input name="firstName" value="" type="hidden"/>
                <input type="button" value="Save"/>
            </form>
        </div>
	</body>
</html>