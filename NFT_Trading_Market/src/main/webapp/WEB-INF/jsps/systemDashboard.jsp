<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
	border: 1px solid black;
}
</style>

<meta charset="ISO-8859-1">
<title>System Dashboard and Statistics</title>
</head>
<body>
	<div align="right">
		<a href="/profile">Back to Profile</a> | <a href="/logout">Logout</a>
	</div>
	<br>
	<div align="center">
		<h2>System Dashboard and Statistics</h2>

		<table>
			<tr>
				<th>Active Sales</th>
				<th>Value</th>
			</tr>
			<tr>
				<td>Active NFT's For Sale</td>
				<td>${countActiveSales}</td>
			</tr>
			<tr>
				<td>Active Priced NFT's For Sale</td>
				<td>${countOfPricesNFT}</td>
			</tr>
			
			<tr>
				<td>Active Auctioned NFT's for Sale</td>
				<td>${countOfAuctionsNFT}</td>
			</tr>
			
			<tr>
				<td>Active NFT Listings With Offers</td>
				<td>${countOfAuctionWithBids}</td>
			</tr>
			
			<tr>
				<td>Active NFT Listings Without Offers</td>
				<td>${countOfAuctionWithoutBids}</td>
			</tr>
			
			<tr>
				<td>All Active Offers</td>
				<td>${countActiveBids}</td>
			</tr>
		</table>

		
</body>
</html>