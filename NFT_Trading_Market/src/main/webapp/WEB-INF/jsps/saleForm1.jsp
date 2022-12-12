<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sale Form</title>
</head>
<body>
	<h2>Create a Sale by filling out the below form</h2>
	<form action="createSale" method="post" >
		Price : <input type="number" step=".000001" min="0" name="price" required/><br>
		Sale Type : <input type="radio" name="saleType" value="Priced" required/>Priced
		<input type="radio" name="saleType" value="Auction" />Auction<br>
		Currency: <input type="radio" name="currency" value="BTC" checked="true" />BTC
		<input type="radio" name="currency" value="ETH" />ETH<br>
		<input type="hidden" name="nftTokenId" value="${nftTokenId}" />
		<input type="submit" value="Create" />
	</form><br>
	<a href="profile">Back to Profile</a><br/>
	<a href="logout">Logout</a>
</body>
</html>