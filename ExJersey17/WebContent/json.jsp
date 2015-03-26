<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>JSON with ReastFull</title>
	<script type="text/javascript" src="resource/js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#json-get').click(function() {
				$.ajax("http://localhost:8080/RestFulJersey17/rest/json/ex",
					{
					    beforeSend: function (xhr) {
					    // $.mobile.showPageLoadingMsg();
					},

					complete: function () {
					// $.mobile.hidePageLoadingMsg();
					},
					    contentType: 'application/json',
					    dataType: 'json',
					    //dataType: 'jsonp',
					    //jsonp: 'callback',
					    type: 'GET',
					    error: function (xhr, ajaxOptions, thrownError) {
					    //alert(xhr.status);
					    //alert(xhr.responseText);
					    //alert(thrownError);
					},
					success: function (data) {
						alert(data.code + " - " + data.name);
						/*
					    var result = data.GetEmployeeListingResult;

					    $.each(result, function (index, output) {
					        $('#EmployeeList').append('<li> <a href=SearchResult.html?productNum=' + output.prod + '> </a>   .....</li>');
					    });

					    //$('#EmployeeList').listview('refresh');*/
					}
				}); 
			});
			$('#json-post').click(function() {
				$.ajax("http://localhost:8080/RestFulJersey17/rest/json/post",
					{
					    beforeSend: function (xhr) {
					    // $.mobile.showPageLoadingMsg();
					},

					complete: function () {
					// $.mobile.hidePageLoadingMsg();
					},
					    contentType: 'application/json',
					    dataType: 'json',
					    //dataType: 'jsonp',
					    //jsonp: 'callback',
					    type: 'POST',
					    data: '{"code":"vn","name":"Viet name"}',
					    error: function (xhr, ajaxOptions, thrownError) {
					    //alert(xhr.status);
					    //alert(xhr.responseText);
					    //alert(thrownError);
					},
					success: function (data) {
						alert(data);
						/*
					    var result = data.GetEmployeeListingResult;

					    $.each(result, function (index, output) {
					        $('#EmployeeList').append('<li> <a href=SearchResult.html?productNum=' + output.prod + '> </a>   .....</li>');
					    });

					    //$('#EmployeeList').listview('refresh');*/
					}
				}); 
			});
		});
	</script>
</head>
<body>

	<fieldset>
		<legend>Connect to Hello ReastFull</legend>
		<input id="json-get" type="button" value="rest/hello/json-get">
		<input id="json-post" type="button" value="rest/hello/json-post">
	</fieldset>

</body>
</html>