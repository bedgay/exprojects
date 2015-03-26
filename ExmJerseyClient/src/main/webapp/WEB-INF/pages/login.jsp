<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="format-detection" content="telephone=no">
<meta name="keywords" content="">
<meta name="description" content="">
<title>MIXJUKE</title>
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"/>

<script src="<c:url value="/resources/js/mixjuke.core.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/mixjuke.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/mixjuke.js"/>" type="text/javascript"></script>
</head>
<body id="body">
	<section class="mixjuke-wrapper-0001-01 p-b-0">
	
		<p id="input_invalid" class="mixjuke-error-message-0001 m-b-25 t-a-c">
			<!-- mopita IDまたはパスワードが違います  -->
		</p>
		<form id="login_form" method="post" data-ajax="false" action="">
			<div class="p-l-15 p-r-15">
				<div class="clearfix">
					<label class="mixjuke-label-0001-01 flt-l"><spring:message code="login_username" /></label>
				</div>
				<!-- 
				<input class="mixjuke-input-text-0001-01 m-t-3" type="text" placeholder="半角英数6～30字で入力してください">
				 -->
				<input class="mixjuke-input-text-0001-01 m-t-3" type="text" name="username" id="username" value="" placeholder=<spring:message code="hint_input_ID" /> >
			</div>
			<div class="p-l-15 p-r-15 m-t-40">
				<div class="clearfix">
					<label class="mixjuke-label-0001-01 flt-l"><spring:message code="login_password" /></label>
					<p class="t-a-r l-h-16 f-s-13"><a href="<spring:eval expression="@applicationProperties.getProperty('forgot_password')"/>" target="_blank">パスワードを忘れた方</a></p>
				</div>
				<!-- 
				<input class="mixjuke-input-text-0001-01 m-t-3" type="password" placeholder="半角英数4~40字で入力してください">
				 -->
				<input class="mixjuke-input-text-0001-01 m-t-3" type="password" name="password" id="password" value="" autocomplete="off" placeholder=<spring:message code="hint_input_pwd" />>
			</div>
			<div class="t-a-c m-t-40">
				<div>
					<img src="<c:url value="/stickyImg"/>" id="captcha_image" width="153" height="53">
				</div>
				<p class="f-s-13 m-t-10"><a id="reload_captcha" href="#">別の画像に変更する</a></p>
				<div class="p-l-15 p-r-15 m-t-20">
					<div class="clearfix">
						<label class="mixjuke-label-0001-01 flt-l">画像の文字を入力してください</label>
					</div>
					<!-- 
					<input class="mixjuke-input-text-0001-01 m-t-3" type="text">
					 -->
					<input class="mixjuke-input-text-0001-01 m-t-3" type="text" id="answer" name="answer"/>
				</div>
			</div>
			<p class="t-a-c f-s-13 l-h-13 m-t-20">
				<a href="<spring:eval expression="@applicationProperties.getProperty('user_agreement')"/>" target="_blank">mopita 利用規約</a>
			</p>
			<div class="m-t-20 t-a-c p-b-20">
				<p class="mixjuke-btn-type0002-middlewrap">
					<input type="hidden" name="register" id="register" value="${register}"/>
					<input class="mixjuke-btn-type0002-innerwrap" id="loginBtn" type="submit" value="<spring:message code="login_submit_button" />">
				</p>
			</div>
		</form>
	<!--/.mixjuke-wrapper-0001-01--></section>
</body>
</html>