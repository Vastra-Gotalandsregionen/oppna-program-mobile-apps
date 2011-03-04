<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Konferensrum</title>
		
		<link rel="shortcut icon" href="http://www.vgregion.se/VGRimages/favicon.ico" type="image/x-icon" />
		<link rel="apple-touch-icon" href="http://www.vgregion.se/VGRimages/favicon.ico" type="image/x-icon" />
		<link rel="apple-touch-icon-precomposed" href="http://www.vgregion.se/VGRimages/favicon.ico" type="image/x-icon" />
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.mobile-1.0a1.min.css" />
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.4.4.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery.mobile-1.0a1.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/bookmark-bubble.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		
		<meta name="apple-mobile-web-app-capable" content="yes" />
		
		<script>

		function success(pos) {
			$.ajax({
			  url: "facilities/near/" + pos.coords.latitude + "/" + pos.coords.longitude,
			  success: function(data) {
			    $(data).each(function(index, facility) {
			    	var elm = $("<div />")
			    	elm.text(facility.name)
			    	$('#result').append(elm)	
			    })
			    
			    $("#waiting").hide()
			  },
			  error: function(er) {
			  	$("#waiting").hide()
			  	alert("Kunde inte kontakta server, försök igen senare")
			  }
			});

		}
		
		function error(er) {
			$("#waiting").hide()
			alert(er.code)
			alert(er.message)
			alert("Kunde inte lokalisera dig, försök igen senare")
		}

		$(function() {
			$("#search").click(function(event) {
				event.preventDefault()
				navigator.geolocation.getCurrentPosition(success, error);
				//$("#waiting").show()
			})
			
			window.setTimeout(function() {
        		var bubble = new google.bookmarkbubble.Bubble();

			    bubble.hasHashParameter = function() {return false}
			
			    bubble.setHashParameter = function() {}

		    	bubble.showIfAllowed()
			  }, 1000)
		})
		</script>

	</head>
<body> 

<div id="first" data-role="page" data-theme="b">

	<div data-role="header" data-theme="b">
		<h1>Konferensrum nära dig</h1>
	</div>

	<div data-role="content" data-theme="b">
		
		<h2>Sök resurs nära dig</h2>
		<p><a id="search" href="#">Konferensrum</a></p>

		<img id="waiting" src="resources/img/ajax-loader.gif" style="display:nonex" />

		<div id="result">
		</div>
	</div>
</div>


</body>