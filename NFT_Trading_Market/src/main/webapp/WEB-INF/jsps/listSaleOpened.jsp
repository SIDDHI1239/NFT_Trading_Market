<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NFT's Listed for Sale</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20,300,0,0" />
</head>
<body>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Opened Sales</h2>
				<h3><c:out value="${msg}"/></h3>
			</caption>
			<tr>
				<th colspan="4">NFT</th>
				<th colspan="3">Sale Details</th>
			</tr>
			<tr>
				<th>Name</th>
				<th>Type</th>
				<th>Description</th>
				<th>Image</th>
				<th>Type</th>
				<th>Price</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${sales}" var="sale">
			
			<c:choose>
				<c:when test="${sale.closingTime == null}">
					<c:set var = "status" value = "Pending"/>
				</c:when>
				<c:when test="${sale.buyer == null}">
					<c:set var = "status" value = "Canceled"/>
				</c:when>
				<c:otherwise>
					<c:set var = "status" value = "Sold"/>
				</c:otherwise>
			</c:choose>
				
				<tr>
					
					<td><c:out value="${sale.nft.name}" /></td>
					<td><c:out value="${sale.nft.type}" /></td>
					<td><c:out value="${sale.nft.description}" /></td>
					<td><c:out value="${sale.nft.imageUrl}" /></td>
					
					<td><c:out value="${sale.type}" /></td>
					<td><c:out value="${sale.expectedValue}" /></td>
					<td>
						<a href="/sale/makeOffer/<c:out value="${sale.id}" />">
  							<span class="material-symbols-outlined">currency_bitcoin</span>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
	<div align="center">
		<a href="/profile">Back to Profile</a><br/>
		<a href="/logout">Logout</a>
	</div>
</body>
</html>