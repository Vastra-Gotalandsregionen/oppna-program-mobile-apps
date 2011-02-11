<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Administration - mobile apps</title>
		
		<link rel="shortcut icon" href="http://www.vgregion.se/VGRimages/favicon.ico" type="image/x-icon" />
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/typography.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/forms.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/urlservice.css" type="text/css" />

	</head>
	<body>
		<h2>Skrivare</h2>
		
		<form action="admin/printers" method="post">
			<div style="height:0px; width:0px; position:absolute; overflow:hidden">
				<!-- Hack, make sure the add action happens on user pressing Enter -->
    			<input type="submit" name="add" />
			</div>
		
			<table>
				<tr>
					<th>Namn</th>
					<th></th>
				</tr>
				<c:forEach var="printer" items="${printers}">
					<tr>
						<td>${printer.name}</td>
						<td><a href="${pageContext.request.contextPath}/printer/${printer.id}">Visa</a></td>
						<td><a href="${pageContext.request.contextPath}/qr?url=${appurl}/printer/${printer.id}">QR</a></td>
					</tr>
				</c:forEach>
					<tr>
						<td><input name="name"></td>
						<td><input type="submit" value="Lägg till" name="add">
					</tr>
			</table>
		</form>		
	</body>
</html>
