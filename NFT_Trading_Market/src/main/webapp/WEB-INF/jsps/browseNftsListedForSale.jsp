<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Browse NFT's Listed for Sale</title>
</head>
<body>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>NFT's Listed for Sale</h2>
			</caption>
			<tr>
				<th>Name</th>
				<th>Type</th>
				<th>Description</th>
				<th>Image URL</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${nfts}" var="nft">
				<tr>
					<td><c:out value="${nft.name}" /></td>
					<td><c:out value="${nft.type}" /></td>
					<td><c:out value="${nft.description}" /></td>
					<td><c:out value="${nft.imageUrl}" /></td>
					<td>
					<form action="offersReceived" method="post">
						<input type="hidden" name="nftId" value="${nft.id}" />
						<input type="submit" value="Offers Received" />
					</form><br/>
					<%-- <form action="deposit" method="post">
						<input type="hidden" name="walletId" value="${wallet.walletId.user.id}" />
						<input type="hidden" name="symbol" value="${wallet.walletId.cryptocurrency.symbol}" />
						<input type="submit" value="Cancel" />
					</form> --%>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
	<div align="center">
		<a href="profile">Back to Profile</a><br/>
		<a href="logout">Logout</a>
	</div>
</body>
</html>