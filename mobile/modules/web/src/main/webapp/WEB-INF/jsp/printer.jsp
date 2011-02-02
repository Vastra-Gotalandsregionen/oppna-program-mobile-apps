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

	</head>
<body> 

<div id="first" data-role="page">

	<div data-role="header" data-theme="b">
		<h1>${printer.name}</h1>
	</div>

	<div data-role="content" data-theme="b">	
		<p><a href="#info">Information</a></p>
		<p><a href="#help">Hjälp</a></p>
		<p><a href="#report">Felanmäl</a></p>
	</div>

	<div data-role="footer" class="ui-bar" data-position="fixed" data-id="menu" data-theme="b">
		<a href="index.html" data-role="button" data-icon="delete">Konferensrum</a>
	</div>
</div>

<div id="info" data-role="page">

	<div data-role="header">
		<h1>Information om ${printer.name}</h1>
	</div>

	<div data-role="content">	
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur condimentum lacinia faucibus. Donec scelerisque nulla at turpis sagittis facilisis sed nec mi. Donec iaculis tincidunt varius. Vestibulum placerat lacus quis urna iaculis vitae scelerisque arcu fermentum. Integer consectetur enim sed purus laoreet sollicitudin sit amet vel libero. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec vitae ipsum a diam eleifend interdum a ac justo. In in erat in neque semper cursus sit amet non sem. </p>		
	</div>

	<div data-role="footer" class="ui-bar" data-position="fixed" data-id="menu">
		<a href="index.html" data-role="button" data-icon="delete">Skrivare</a>
		<a href="index.html" data-role="button" data-icon="delete">Konferensrum</a>
	</div>
</div>

<div id="help" data-role="page">

	<div data-role="header">
		<h1>Hjälp för ${printer.name}</h1>
	</div>

	<div data-role="content">	
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur condimentum lacinia faucibus. Donec scelerisque nulla at turpis sagittis facilisis sed nec mi. Donec iaculis tincidunt varius. Vestibulum placerat lacus quis urna iaculis vitae scelerisque arcu fermentum. Integer consectetur enim sed purus laoreet sollicitudin sit amet vel libero. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec vitae ipsum a diam eleifend interdum a ac justo. In in erat in neque semper cursus sit amet non sem. </p>		
	</div>

	<div data-role="footer" class="ui-bar" data-position="fixed" data-id="menu">
		<a href="index.html" data-role="button" data-icon="delete">Skrivare</a>
		<a href="index.html" data-role="button" data-icon="delete">Konferensrum</a>
	</div>

</div>

<div id="report" data-role="page">

	<div data-role="header">
		<h1>Felanmäl ${printer.name}</h1>
	</div>

	<div data-role="content">	
		<form action=".">

		    <p><label for="error">Beskrivning av fel</label><textarea name="error" id="error"></textarea></p>
		    <p><label for="reporter">Ditt VGRid (valfri)</label><input name="reporter" id="reporter"></p>
		    <div class="ui-grid-a">
		        <div class="ui-block-a"><input type="submit" value="Skicka"></div>
		        <div class="ui-block-b"><input type="button" value="Avbryt"></div>
		    </div>
	</div>

	<div data-role="footer">
		<h4>Page Footer</h4>
	</div>
</div>


</body>