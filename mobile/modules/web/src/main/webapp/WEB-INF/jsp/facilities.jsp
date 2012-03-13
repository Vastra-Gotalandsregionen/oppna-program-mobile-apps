<%--

    Copyright 2010 Västra Götalandsregionen

      This library is free software; you can redistribute it and/or modify
      it under the terms of version 2.1 of the GNU Lesser General Public
      License as published by the Free Software Foundation.

      This library is distributed in the hope that it will be useful,
      but WITHOUT ANY WARRANTY; without even the implied warranty of
      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
      GNU Lesser General Public License for more details.

      You should have received a copy of the GNU Lesser General Public
      License along with this library; if not, write to the
      Free Software Foundation, Inc., 59 Temple Place, Suite 330,
      Boston, MA 02111-1307  USA

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Konferensrum</title>
		
		<link rel="shortcut icon" href="http://www.vgregion.se/VGRimages/favicon.ico" type="image/x-icon" />
		<link rel="apple-touch-icon" href="http://www.vgregion.se/VGRimages/favicon.ico" type="image/x-icon" />
		<link rel="apple-touch-icon-precomposed" href="http://www.vgregion.se/VGRimages/favicon.ico" type="image/x-icon" />
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/app/resources/css/jquery.mobile-1.0a1.min.css" />
		<script src="${pageContext.request.contextPath}/app/resources/js/jquery-1.4.4.js"></script>
		<script src="http://code.jquery.com/mobile/1.0a4.1/jquery.mobile-1.0a4.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/app/resources/js/bookmark-bubble.js"></script>
		
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		
		<meta name="apple-mobile-web-app-capable" content="yes" />
		
		<script>

		function success(pos) {
		    console.log(pos)
			$.ajax({
			  url: "facilities/near/" + pos.coords.latitude + "/" + pos.coords.longitude,
			  success: function(data) {
			    $('#result').empty()
			    var map = showMap(pos)
			    
			    $(data).each(function(index, facility) {
			    	var elm = $("<div />")
			    	elm.text(facility.name)
			    	$('#result').append(elm)
			    	
			    	showRoomOnMap(map, facility.position, facility.name) 
			    })
			    
			    $("#waiting").hide()
			    $("#search").hide()
			  },
			  error: function(er) {
			  	$("#waiting").hide()
			  	alert("Kunde inte kontakta server, försök igen senare")
			  }
			});

		}
		
		function showMap(pos) {
			var myLatlng = new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude);
  			var myOptions = {
    			zoom: 12,
    			center: myLatlng,
    			mapTypeId: google.maps.MapTypeId.ROADMAP
  			}
  			var map = new google.maps.Map(document.getElementById("map"), myOptions)
    		return map
		}
		
		function showRoomOnMap(map, pos, name) {
		console.log(name)
  			var marker = new google.maps.Marker({
      			position: new google.maps.LatLng(pos.latitude, pos.longitude), 
      			map: map, 
      			title: name
  			})
		
		}
		
		function error(er) {
			$("#waiting").hide()
			alert("Kunde inte lokalisera dig, försök igen senare")
		}

		$(function() {
			$("#search").click(function(event) {
				event.preventDefault()
				navigator.geolocation.getCurrentPosition(success, error);
				$("#waiting").show()
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

		<img id="waiting" src="resources/img/ajax-loader.gif" style="display:none" />

		<div id="map" style="width:100%; height:400px; margin-bottom: 15px"></div>
		
		<div id="result"></div>
		
	</div>
</div>


</body>