<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spring</title>
        
        <spring:url value="/resources/js" var="js_url" />
        <spring:url value="/resources/jquery-ui" var="ui_url" />
        <spring:url value="/resources/css" var="css_url" />
        <spring:url value="/security/login" var="login_url" />
        <spring:url value="/security/logout" var="logout_url" />
        
        <link href="${css_url}/style.css" rel="stylesheet" type="text/css"/>
        <link href="${ui_url}/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <!-- 
        <link href="${ui_url}/jquery-ui.structure.min.css" rel="stylesheet" type="text/css"/>
        <link href="${ui_url}/jquery-ui.theme.min.css" rel="stylesheet" type="text/css"/>
        -->
        <script type="text/javascript" src="${js_url}/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="${ui_url}/jquery-ui.min.js"></script>
        <script type="text/javascript">
        $(function() {
            $('#loginDialog b').html('');
            $('#loginDialog').css('display', '').dialog();
            $('#login').click(function() {
                $('#loginDialog b').html('');
                $('#loginDialog').css('display', '').dialog();
            });
            $('#loginDialog input[value="Login"]').click(function() {
                var form = $('#loginDialog form');               
                $.ajax({
                    type: form.attr('method'),
                    url: form.attr('action'),
                    data: form.serialize(),
                    success: function(data) {   
                        if (data.RESULT) {
                            $('#loginDialog').dialog('close');
                            $('#loginResult').html('Login success!');
                        } else {
                            $('#loginDialog b').html('Login fail!');
                        }
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        $('#loginDialog b').html('Login fail!');
                    }
                });
            });
            $('#logout').click(function() {
                $.ajax({
                    type: 'GET',
                    url: '${logout_url}',
                    success: function(data) {   
                        if (data.RESULT) {
                            $('#loginResult').html('...');
                            $('#logoutResult').html('Logout success!');
                            $('#loginDialog b').html('');
                            $('#loginDialog').css('display', '').dialog();
                        } else {
                            alert("Logout error!");
                        }
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        alert("Logout error!");
                    }
                });
            });
        });
        </script>
    </head>
    <body>
        <center>
            <table class="content" cellspacing="1">
	            <tr>
	                <td width="25%">Action</td>
	                <td>Result</td>
	            </tr>
                <tr>
                    <td id="login">Login</td>
                    <td id="loginResult">...</td>
                </tr>
                <tr>
                    <td id="logout">Logout</td>
                    <td id="logoutResult">...</td>
                </tr>
            </table>
        </center>
  
        <div id="loginDialog" title="Login dialog">
            <form action="${login_url}" method="post">
                <b>Msg</b><br/>
                Username: <input name="username"/><br/> 
                Password: <input name="password"/><br/>
                <input type="button" value="Login"/>
            </form>
        </div>
    </body>
</html>