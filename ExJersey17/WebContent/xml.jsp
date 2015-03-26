<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>XML with ReastFull</title>
	<script type="text/javascript" src="resource/js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#xml-get').click(function() {
				$.ajax("http://localhost:8080/RestFulJersey17/rest/xml/country/ex",
					{
					    beforeSend: function (xhr) {
					},

					complete: function () {
					},
					    contentType: 'application/xml',
					    dataType: 'xml',
					    type: 'GET',
					    error: function (xhr, ajaxOptions, thrownError) {
					},
					success: function (data) {
						alert(data);
					}
				}); 
			});
			$('#xml-post').click(function() {
				$.ajax("http://localhost:8080/RestFulJersey17/rest/xml/country",
					{
					    beforeSend: function (xhr) {
					},

					complete: function () {
					},
					    contentType: 'application/xml',
					    dataType: 'xml',
					    //dataType: 'jsonp',
					    //jsonp: 'callback',
					    type: 'POST',
					    data: '<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Country Name="au\'s name"><Code>au</Code></Country>',
					    error: function (xhr, ajaxOptions, thrownError) {
					},
					success: function (data) {
						alert(data);
					}
				}); 
			});
		});
	</script>
</head>
<body>

	<fieldset>
		<legend>Connect to XML ReastFull</legend>
		<input id="xml-get" type="button" value="rest/xml/country/ex">
		<input id="xml-post" type="button" value="rest/xml/country">
	</fieldset>

</body>
</html>