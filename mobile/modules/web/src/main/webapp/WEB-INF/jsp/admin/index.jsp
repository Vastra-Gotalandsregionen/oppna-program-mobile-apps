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
		<title>Administration - mobile apps</title>
		
		<link rel="shortcut icon" href="http://www.vgregion.se/VGRimages/favicon.ico" type="image/x-icon" />
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/api/resources/css/reset.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/api/resources/css/typography.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/api/resources/css/forms.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/api/resources/css/urlservice.css" type="text/css" />

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
					<tr class="printer">
						<td>${printer.name}</td>
						<td><a href="${pageContext.request.contextPath}/printer.html?printer=${printer.id}">Visa</a></td>
						<td><a class="qr" href="${pageContext.request.contextPath}/api/qr?url=${appurl}/printer/${printer.id}">QR</a></td>
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
