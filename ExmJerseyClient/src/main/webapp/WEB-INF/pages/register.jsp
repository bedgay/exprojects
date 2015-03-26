<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="ja">
<head>
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
<meta name="format-detection" content="telephone=no"/>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<title>MIXJUKE</title>
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"/>

<script src="<c:url value="/resources/js/mixjuke.core.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/mixjuke.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/mixjuke.js"/>" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	$('#input_invalid').change(function() {
		$('#register_desc').css('display', 'none');
	});
});
</script>
</head>
<body id="body">
	<section class="mixjuke-wrapper-0001-01 p-b-0">
		<p class="f-s-15 p-b-6 p-t-6 m-b-25" id="register_desc">
			mopitaは（株）エムティーアイが提供する会員認証サービスです。<br>
			mopitaのアカウントを作成すると、提携する全てのサービスを共通のアカウントでご利用いただけます。&nbsp;&nbsp;※登録無料
		</p>
			<div class="mixjuke-error-message-0001 m-b-25 t-a-c" id="input_invalid"></div>
			<form id="register_form" method="post" data-ajax="false">
			<div class="p-l-15 p-r-15">
				<div class="clearfix">
					<label class="mixjuke-label-0001-01 flt-l"><spring:message code="register_username" /></label>
				</div>
				<input class="mixjuke-input-text-0001-01 m-t-3" type="text" name="username" id="username" value="" placeholder=<spring:message code="hint_input_ID" />> 
			</div>
			<div class="p-l-15 p-r-15 m-t-30">
				<div class="clearfix">
					<label class="mixjuke-label-0001-01 flt-l"><spring:message code="register_password" /></label>
				</div>
				<input class="mixjuke-input-text-0001-01 m-t-3" type="password" name="password" id="password" value="" autocomplete="off" placeholder=<spring:message code="hint_input_pwd" />>
			</div>
			<div class="p-l-15 p-r-15 m-t-30">
				<div class="clearfix">
					<label class="mixjuke-label-0001-01 flt-l"><spring:message code="register_password" />（確認用）</label>
				</div>
				<input class="mixjuke-input-text-0001-01 m-t-3" type="password" name="repassword" id="repassword" value="" autocomplete="off" placeholder=<spring:message code="hint_input_pwd" />>
			</div>
			<p class="t-a-c f-s-13 l-h-13 m-t-20">
				<a href="<spring:eval expression="@applicationProperties.getProperty('user_agreement')"/>" target="_blank">mopita 利用規約</a>
			</p>
			<div class="m-t-20 t-a-c p-b-20">
				<p class="mixjuke-btn-type0002-middlewrap">
					<input type="hidden" name="register" id="register" value="${register}"/>
					<input class="mixjuke-btn-type0002-innerwrap" id="nextBtn" type="submit" value="<spring:message code="register_next_button" />">
				</p>
			</div>
		</form>
	<!--/.mixjuke-wrapper-0001-01--></section>
</body>
</html>