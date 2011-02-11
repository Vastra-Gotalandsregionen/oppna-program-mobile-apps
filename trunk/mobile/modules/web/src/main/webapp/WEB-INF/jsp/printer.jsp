<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Skrivare</title>
		
		<link rel="shortcut icon" href="http://www.vgregion.se/VGRimages/favicon.ico" type="image/x-icon" />
		
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0a1/jquery.mobile-1.0a1.min.css" />
		<script src="http://code.jquery.com/jquery-1.4.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.0a1/jquery.mobile-1.0a1.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		
		<script>
		
		$(function() {
			$('#first').bind('pagehide',function(event, ui){
  				$("#notice").hide();
			});
		})
		
		</script>

	</head>
<body> 

<div id="first" data-role="page" data-theme="b">

	<div data-role="header" data-theme="b">
		<h1>${printer.name}</h1>
	</div>

	<div data-role="content" data-theme="b">
		<div id="notice">
			<c:if test="${!empty notice}">
				<p>${notice}</p>
			</c:if>
		</div>
		
		<p><a href="#info">Information</a></p>
		<p><a href="#help">Hjälp</a></p>
		<p><a href="#report">Felanmäl</a></p>
	</div>
</div>

<div id="info" data-role="page" data-theme="b">

	<div data-role="header" data-theme="b">
		<h1>${printer.name} - information</h1>
	</div>

	<div data-role="content" data-theme="b">	
		<p>${printer.information}</p>		
	</div>
</div>

<div id="help" data-role="page" data-theme="b">

	<div data-role="header" data-theme="b">
		<h1>${printer.name} - hjälp</h1>
	</div>

	<div data-role="content" data-theme="b">	
		<p>${printer.help}</p>		
	</div>

</div>

<div id="report" data-role="page" data-theme="b">

	<div data-role="header" data-theme="b">
		<h1>${printer.name} - felanmäl</h1>
	</div>

	<div data-role="content" data-theme="b">	
		<form action="${pageContext.request.contextPath}/printer/${printer.id}/report" method="POST">

		    <p><label for="error">Beskrivning av fel</label><textarea name="error" id="error"></textarea></p>
		    <p><label for="reporter">Ditt VGRid (valfri)</label><input name="reporter" id="reporter"></p>
		    <div class="ui-grid-a">
		        <div class="ui-block-a"><input type="submit" value="Skicka"></div>
		        <div class="ui-block-b"><input type="button" value="Avbryt"></div>
		    </div>
	</div>
</div>


</body>