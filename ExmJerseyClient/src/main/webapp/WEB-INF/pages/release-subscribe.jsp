<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Release Free Service</title>
<%-- <meta name="viewport" content="width=device-width, initial-scale=.5"> --%>
<link href="<c:url value="/resources/css/mixjuke.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/mixjuke.private.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/js/mixjuke.core.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/mixjuke.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/mixjuke.js"/>" type="text/javascript"></script>

</head>
<body onload="onLoadReleasePage()">
	<div data-role="page" id="release-subscribe-page">
		<div data-role="header" data-theme="b">
			<h1>Mixjuke</h1>
		</div>
		<div data-role="content">
		<div id="input_invalid"></div>
		<input type="hidden" name="code" id="code" value="${model.code}"/>
		<input type="hidden" name="msg" id="msg" value="${model.message}"/>
		<input type="hidden" name="hiddenForm" id="hiddenForm" value="${hidden}"/>
			<form id="release_subcribe_form" method="post" data-ajax="false" hidden="true">
				<label for="text-basic">Release subscribe!</label>
				<button type="submit" id="releaseSubscribeBtn" value="" data-theme="b">Release</button>
			</form>
		</div>
	</div>
</body>
</html>