<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Personal Transaction Statistics</title>
</head>
<body>
<div align="center">
		<table border="1" cellpadding="5">
			<h2>${msg}</h2>
			
			<form action="sortTransactions" method="post" >
				Period : <input type="radio" name="days" value="1" required/>24 hours
				<input type="radio" name="days" value="7" required/>Last Week
				<input type="radio" name="days" value="30" required/>Last Month<br>
				Currency: <input type="radio" name="currency" value="BTC" required/>BTC
				<input type="radio" name="currency" value="ETH" required/>ETH<br>
				<%-- <input type="hidden" name="nftTokenId" value="${nftTokenId}" /> --%>
				<input type="submit" value="Sort" /><br>
			</form><br>
			
			<tr>
				<th>Txn ID</th>
				<th>Date & Time</th>
				<th>Action Type</th>
				<th>NFT Name</th>
				<th>Currency Type</th>
				<th>Amount</th>
				<th>Remaining Balance</th>
			</tr>
			<c:forEach items="${transactions}" var="transaction">
				<tr>
					<td><c:out value="${transaction.id}" /></td>
					<td><c:out value="${transaction.transactionDate}" /></td>
					<td><c:out value="${transaction.transactionType}" /></td>
					<td><c:out value="${transaction.nft.name}" /></td>
					<td><c:out value="${transaction.cryptocurrency.symbol}" /></td>
					<td><c:out value="${transaction.transctionAmount}" /></td>
					<td><c:out value="${transaction.remainderBalance}" /></td>
					<%-- <td><a href="sale/new/${nft.tokenId}">Sell</a></td> --%>
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