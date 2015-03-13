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
<body id="body" onload="onLoad()">
<!--
    <header>
        <h1 style="background:#7d878b;line-height:55px;text-align:center;color:#fff;font-size:20px;">&nbsp;</h1>
    </header>
-->
    <section class="mixjuke-wrapper-0001-01 p-b-0">
        <form id="confirm_register_form" method="post" data-ajax="false">
            <div class="p-l-15 p-r-15">
                <div class="clearfix">
                    <label class="mixjuke-label-0001-01 flt-l"><spring:message code="register_username" /></label>
                </div>
                <!-- <p class="mixjuke-input-detail-confirm-0001"> -->
                   <input class="mixjuke-input-text-0001-01 m-t-3" disabled="disabled" type="text" name="confirm_username" id="confirm_username" value="" placeholder=<spring:message code="hint_input_ID" />>
                <!-- </p> -->
            </div>
            <div class="p-l-15 p-r-15 m-t-30">
                <div class="clearfix">
                    <label class="mixjuke-label-0001-01 flt-l"><spring:message code="register_password" /></label>
                </div>
                <!-- <p class="mixjuke-input-detail-confirm-0001"> -->
                    <input class="mixjuke-input-text-0001-01 m-t-3" disabled="disabled" type="password" name="confirm_password" id="confirm_password" value="" autocomplete="off" placeholder=<spring:message code="hint_input_pwd" />>
                <!-- </p> -->
            </div>
            <div class="m-t-30 t-a-c p-b-20">
                <p class="mixjuke-btn-type0002-middlewrap">
                    <input type="hidden" name="register" id="register" value="${register}"/>
                    <input class="mixjuke-btn-type0002-innerwrap" id="confirmBtn" type="submit" value="<spring:message code="register_button" />">
                </p>
            </div>
        </form>
    <!--/.mixjuke-wrapper-0001-01--></section>
</body>
</html>
