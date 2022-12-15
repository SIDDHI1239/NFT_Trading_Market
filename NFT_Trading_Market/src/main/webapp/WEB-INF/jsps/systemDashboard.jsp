<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>System Dashboard and Statistics</title>
		
		<link href="/css/style.css" rel="stylesheet" type="text/css">
		
		<style>
		
			#dashboard>tbody>tr>td:nth-child(1) { text-align:left; }
		
		</style>
		
	</head>
	<body>
		<div align="center" id="main">
			<div align="right">
				<a href="/profile">Back to Profile</a> | <a href="/logout">Logout</a>
			</div>
			<br>
			<div align="center">
			
				<h2>System dashboard</h2>
				
				<table style="width:25%; text-align:center;" id="dashboard">
					<thead>
						<tr>
							<th>Description</th>
							<th>Quantity</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Total of active sales</td>
							<td >${countActiveSales}</td>
						</tr>
						<tr>
							<td>Total of active priced sales</td>
							<td>${countOfPricesNFT}</td>
						</tr>
						
						<tr>
							<td>Total of active auction sales</td>
							<td>${countOfAuctionsNFT}</td>
						</tr>
						
						<tr>
							<td>Total of auction sales with offers</td>
							<td>${countOfAuctionWithBids}</td>
						</tr>
						
						<tr>
							<td>Total of auction sales without offers</td>
							<td>${countOfAuctionWithoutBids}</td>
						</tr>
						
						<tr>
							<td>Total of active offers</td>
							<td>${countActiveBids}</td>
						</tr>
					</tbody>
				</table>
		
			</div>
			
			<div align="center">
			
				<h2>System transaction statistics</h2>
				
				<form action="/systemDashboard" method="post" >
						 
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
		
				<table style="width:70%; text-align:center;">
				
					<thead>
						<tr>
							<th>Cryptocurrency</th>
							<th>Total deposits</th>
							<th>Deposits balance</th>
							<th>Total withdraws</th>
							<th>withdraws balance</th>
							<th>Initial system balance</th>
							<th>Current system balance</th>
							<th>Total of Sales</th>
							<th>Sales balance</th>
						</tr>
					</thead>
					
					<tbody>
						<c:if test = "${currency == 'BTC' || currency == 'ALL'}">
							<tr>
								<td>Bitcoin</td>
								<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${btc[0]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="5" value="${btc[1]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${btc[2]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="5" value="${btc[3]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="5" value="${btc[4]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="5" value="${btc[5]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${btc[6]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="5" value="${btc[7]}"/></td>
							</tr>
						</c:if>
						
						<c:if test = "${currency == 'ETH' || currency == 'ALL'}">
							<tr>
								<td>Ethereum</td>
								<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${eth[0]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="5" value="${eth[1]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${eth[2]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="5" value="${eth[3]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="5" value="${eth[4]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="5" value="${eth[5]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${eth[6]}"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="5" value="${eth[7]}"/></td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>