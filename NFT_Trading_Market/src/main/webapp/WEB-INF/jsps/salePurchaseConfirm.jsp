<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Purchase sale confirmation</title>
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20,300,0,0" />
	</head>
	<body>
		
		<table border="0" cellpadding="5" style="text-align:left; vertical-align:top;" >
			
			<caption> <h3>Sale purchase</h3> </caption>
			
			<tr>
				<th>Name: </th>
				<td><c:out value="${sale.nft.name}" /></td>
			</tr>
			<tr>
				<th>Type:</th>
				<td><c:out value="${sale.nft.type}" /></td>
			</tr>
			
			<tr>	
				<th>Description:</th>
				<td><c:out value="${sale.nft.description}" /></td>
			</tr>
			
			<tr>
				<th>Price:</th>
				<td><c:out value="${sale.expectedValue}" /> <c:out value="${sale.cryptocurrency.symbol}" /></td>
			</tr>
			
			<tr>
				<th style="vertical-align:top;">Image:</th>
				<td><img src="<c:out value="${sale.nft.imageUrl}" />" alt="NFT" style="width:200px;"></td>
			</tr>
			<tr>
				<th></th>
				<td>
					<a href="/sale/purchase/<c:out value="${sale.id}" />">
  						Confirm purchase <span class="material-symbols-outlined">check</span>
					</a>
				</td>
			</tr>
			
		</table>
		
	</body>
	
</html>