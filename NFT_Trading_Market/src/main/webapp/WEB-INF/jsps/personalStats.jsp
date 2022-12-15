<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Personal Transaction Statistics</title>
		<link href="/css/style.css" rel="stylesheet" type="text/css">
		
		<link href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
		<link href="https://cdn.datatables.net/responsive/2.4.0/css/responsive.dataTables.min.css" rel="stylesheet" type="text/css">
		
		<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
		<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
		<script src="https://cdn.datatables.net/responsive/2.4.0/js/dataTables.responsive.min.js"></script>
		
		<style>
		
			#table_content { max-width: 650px }
		
		</style>
		
		<script>
		
			$(document).ready(function() {
				
				$('#transaction_table').DataTable( {
			    	
			    	ordering: false,
			    	searching: false,
			        info: false,
			        responsive: true,
			        
			    } );
			} );
			
		</script>
	</head>
	<body>
		<div align="center" id="main">
			<div align="right">
				<a href="/profile">Back to Profile</a> | <a href="/logout">Logout</a>
			</div>
			<br>
			<div id="table_content" align="center">
			
				<h2>${msg}</h2>
				
				<form action="/viewPersonalStats" method="post" >
						 
					<label for="days">Period: </label>
					<select id="days" name="days">
						<option value="1" <c:if test="${days == 1}">selected</c:if>>24 hours</option>
						<option value="7" <c:if test="${days == 7}">selected</c:if>>Last Week</option>
						<option value="30" <c:if test="${days == 30}">selected</c:if>>Last Month</option>
					</select>
						
					<label for="currency">Currency: </label>
					<select id="currency" name="currency">
						<option value="BTC" <c:if test="${currency == 'BTC'}">selected</c:if>>Bitcoin</option>
						<option value="ETH" <c:if test="${currency == 'ETH'}">selected</c:if>>Ethereum</option>
						<option value="ALL" <c:if test="${currency == 'ALL'}">selected</c:if>>All Currencies</option>
					</select>
					
					<input type="submit" value="Filter"/> 
					
				</form>
					
				<br/>
				
				<table id="transaction_table" class="display nowrap" border="0" cellpadding="5" style="width:100%">
					<thead>
						<tr>
							<th>Date & Time</th>
							<th>Action Type</th>
							<th>NFT Name</th>
							<th>Currency Type</th>
							<th>Amount</th>
							<th>Remaining Balance</th>
							<th>Sale's type</th>
							<th>Seller</th>
							<th>Buyer</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${transactions}" var="transaction">
							<tr>
								<td class="center"><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${transaction.transactionDate}" /></td>
								<td>${transaction.transactionType}</td>
								<td>${transaction.nft.name}</td>
								<td class="center">${transaction.cryptocurrency.symbol}</td>
								<td class="center"><fmt:formatNumber type="number" maxFractionDigits="5" value="${transaction.transactionAmount}"/></td>
								<td class="center"><fmt:formatNumber type="number" maxFractionDigits="5" value="${transaction.remainderBalance}"/></td>
								<td class="center">${transaction.sale.type}</td>
								<td>${transaction.sale.seller.nickName}</td>
								<td>${transaction.sale.buyer.nickName}</td>
							</tr>
						</c:forEach>
					<tbody>
				</table>
			</div>
		</div>
	</body>
</html>