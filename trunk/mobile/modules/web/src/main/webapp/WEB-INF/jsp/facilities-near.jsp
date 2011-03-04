<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Konferensrum</title>
		
		<link rel="shortcut icon" href="http://www.vgregion.se/VGRimages/favicon.ico" type="image/x-icon" />
		
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0a1/jquery.mobile-1.0a1.min.css" />
		<script src="http://code.jquery.com/jquery-1.4.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.0a1/jquery.mobile-1.0a1.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		
	</head>
<body> 

<div id="first" data-role="page" data-theme="b">

	<div data-role="header" data-theme="b">
		<h1>Konferensrum nära dig</h1>
	</div>

	<div data-role="content" data-theme="b">
		<ul>
			<c:forEach var="facility" items="${facilities}">
				<li>${facility.name}</li>
			</c:forEach>
		</ul>
	</div>
</div>


</body>