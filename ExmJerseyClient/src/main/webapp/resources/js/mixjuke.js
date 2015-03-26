$(document).on('click', '#reload_captcha', function(e) {
    var d = new Date();
    $("#captcha_image").attr("src", "/web/stickyImg?"+d.getTime());
});

$(document).on('click', '#loginBtn', function(e) {
	loginBtnClick('/web/login');
	return false;
});

$(document).on('click', '#nextBtn', function(e) {
	nextBtnClick('/web/register');
	return false;
});

$(document).on('click', '#confirmBtn', function(e) {
	registerBtnClick('/web/register');
	return false;
});

$(document).on('click', '#subscribeBtn', function(e) {
	subscribeBtnClick('/web/serviceRegist');
	return false;
});

$(document).on('click', '#releaseSubscribeBtn', function(e) {
	releaseSubscribeBtnClick('/web/serviceRelease');
	return false;
});

function onLoad() {
	$('#confirm_username').val(getCookie('username'));
	$('#confirm_password').val(getCookie('password'));
}
function subscribeBtnClick(url) {
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		error : function(jqXHR, textStatus, errorThrown) {
			$('#input_invalid').text('Internal error!');
		},
		success : function(data, textStatus, jqXHR) {
			if (data.code == 'ERROR') {
				$('#input_invalid').text(data.message);
			} else {
				window.location.href = data.message;
			}
		}
	});
	return false;
}
function releaseSubscribeBtnClick(url) {
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		error : function(jqXHR, textStatus, errorThrown) {
			$('#input_invalid').text('Internal error!');
		},
		success : function(data, textStatus, jqXHR) {
//			if (data.code == 'ERROR') {
//				$('#input_invalid').text(data.message);
//			} else {
//				window.location.href = data.message;
//			}
			$('#code').val(data.code);
			$('#msg').val(data.message);
			onLoadReleasePage();
		}
	});
	return false;
}
function onLoadReleasePage(){
	var hidden = $('#hiddenForm').val();
	if(hidden == 'false'){
		$('#release_subcribe_form').show();
	}
	
	var code = $('#code').val();
	var msg = $('#msg').val();
	if(code == 'ERROR'){
		$('#input_invalid').text(msg);
	}else if(code == 'SUCCESS'){
		window.location.href = msg;
	}
	return false;
}
function loginBtnClick(url) {
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		data : formLoginToJSON(),
		error : function(jqXHR, textStatus, errorThrown) {
			$('#input_invalid').text('Internal error!');
		},
		success : function(data, textStatus, jqXHR) {
			if (data.code == 'ERROR') {
				$('#input_invalid').text(data.message);
			} else {
				window.location.href = data.message;
			}
		}
	});
	return false;
}

function nextBtnClick(url) {
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		data : formRegisterNextToJSON(),
		error : function(jqXHR, textStatus, errorThrown) {
			$('#input_invalid').text('Internal error!').change();
		},
		success : function(data, textStatus, jqXHR) {
			if (data.code == 'ERROR') {
				$('#input_invalid').text(data.message).change();
			} else {
				window.location.href = data.message;
			}
		}
	});
	return false;
}

function registerBtnClick(url) {
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		data : formRegisterConfirmToJSON(),
		error : function(jqXHR, textStatus, errorThrown) {
			$('#input_invalid').text('Internal error!').change();
		},
		success : function(data, textStatus, jqXHR) {
			if (data.code == 'ERROR') {
				$('#input_invalid').text(data.message).change();
			} else {
				window.location.href = data.message;
			}
		}
	});
	return false;
}
// Helper function to serialize all the form fields into a JSON string
function formLoginToJSON() {
	return {
		username : $('#username').val(),
		password : $('#password').val(),
		answer : $('#answer').val(),
		register : $('#register').val()
	};
}
function formRegisterNextToJSON() {
	return {
		username : $('#username').val(),
		password : $('#password').val(),
		repassword : $('#repassword').val(),
		answer : $('#answer').val(),
		checkInputValid: 1,
		register : $('#register').val()
	};
}
function formRegisterConfirmToJSON() {
	return {
		username : $('#confirm_username').val(),
		password : $('#confirm_password').val(),
		register : $('#register').val()
	};
}

function getCookie(c_name) {
	var c_value = document.cookie;
	var c_start = c_value.indexOf(" " + c_name + "=");
	if (c_start == -1) {
		c_start = c_value.indexOf(c_name + "=");
	}
	if (c_start == -1) {
		c_value = null;
	} else {
		c_start = c_value.indexOf("=", c_start) + 1;
		var c_end = c_value.indexOf(";", c_start);
		if (c_end == -1) {
			c_end = c_value.length;
		}
		c_value = unescape(c_value.substring(c_start, c_end));
	}
	return c_value;
}

function setCookie(c_name, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var c_value = escape(value)
			+ ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
	document.cookie = c_name + "=" + c_value;
}