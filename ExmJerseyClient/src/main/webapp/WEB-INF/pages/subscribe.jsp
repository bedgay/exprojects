<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Register Free Service</title>
<%-- <meta name="viewport" content="width=device-width, initial-scale=.5"> --%>
<link href="<c:url value="/resources/css/mixjuke.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/mixjuke.private.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/js/mixjuke.core.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/mixjuke.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/mixjuke.js"/>" type="text/javascript"></script>

</head>
<body>
	<div data-role="page" id="login-page">
		<div data-role="header" data-theme="b">
			<h1>Mixjuke</h1>
		</div>
		<div data-role="content">
		<div id="input_invalid">
		</div>
			<form id="login_form" method="post" data-ajax="false">
				<label for="text-basic">Subscribe to be Mixuke's user now!</label>
				<button type="submit" id="subscribeBtn" value="" data-theme="b">Subscribe</button>
			</form>
		</div>
	</div>
</body>
</html>