<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display NFT's</title>
</head>
<body>
	<h2>NFT's:</h2>

	<table>
		<tr>
			<th>Name</th>
			<th>Type</th>
			<th>Description</th>
			<th>Image URL</th>
			<th>Asset URL</th>
		</tr>
		<c:forEach items="${nfts}" var="nft">
			<tr>
				<td>${nft.name}</td>
				<td>${nft.type}</td>
				<td>${nft.description}</td>
				<td>${nft.imageUrl}</td>
				<td>${nft.assetUrl}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>