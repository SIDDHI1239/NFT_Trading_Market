<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>NFT's Listed for Sale</title>
		
		<link href="/css/style.css" rel="stylesheet" type="text/css">
		<link href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
		
		<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
		<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
		
		<script>
			$(document).ready(function () {
				
				$('#sales_table').DataTable({
				
			        "searching": false,
			    	columnDefs: [ { orderable: false, targets: [0,2,3,5,6,7,8,9,10] } ],
			        order: [[1, 'asc']],
			        
			    });
			    
			});
		</script>
	</head>
	<body>
		<div align="center" id="main">
			<div align="right">
				<a href="/profile">Back to Profile</a> | <a href="/logout" onclick="return confirm('Do you want to logout?');">Logout</a>
			</div>
			<br>
			
					<div>
					
						<div align="center"> 
							<h2>List of Opened Sales</h2>
							<h3>${msg}</h3>
							
							<form action="/sale/listOpened" method="post" >
					 
								<label for="minPrice">Min price: </label> <input type="number" name="minPrice" min="0" max="100000" value="${minPrice}"> &nbsp;
								<label for="maxPrice">Max price: </label> <input type="number" name="maxPrice" min="0" max="100000" value="${maxPrice}"> &nbsp;
									
								<label for="currency">Currency: </label>
								<select id="currency" name="currency">
									<option value="BTC" <c:if test="${currency == 'BTC'}">selected</c:if>>Bitcoin</option>
									<option value="ETH" <c:if test="${currency == 'ETH'}">selected</c:if>>Ethereum</option>
									<option value="ALL" <c:if test="${currency == 'ALL'}">selected</c:if>>All Currencies</option>
								</select>
								
								<input type="submit" value="Filter"/> 
								
							</form>
			
						</div>
						
						<br/>
						
						<table id="sales_table" border="0" cellpadding="5" style="width:100%">
			
							<thead>
								<tr>
							    	<th></th>
							    	<th>Name</th>
							    	<th>Type</th>
							    	<th>Description</th>
							    	<th>Listing time</th>
							    	<th>Type</th>
							    	<th>Currency</th>
							    	<th>Price / Highest offer</th>
							    	<th>My offer</th>
							    	<th>Auction</th>
							    	<th></th>
							  	</tr>
							</thead>
							
							<jsp:useBean id="now" class="java.util.Date"/>
							
							<c:forEach items="${sales}" var="sale">
							
								<c:forEach items="${sale.bids}" var="bid">
								
									<c:if test = "${bid.user.id == userId && bid.expirationTime gt now}">
										<c:set var = "bidId" value = "${bid.id}"/>
										<c:set var = "bidValue" value = "${bid.bidValue}"/>
									</c:if>
									
									<c:if test = "${ bid.expirationTime gt now}">
										<c:set var = "highestBidValue" value = "${bid.bidValue}"/>
										<c:set var = "highestBidId" value = "${bid.user.id}"/>
									</c:if>
								
								</c:forEach>
								
								<c:choose>
									<c:when test="${highestBidValue > 0}">
										<c:set var = "expectedValue" value = "${highestBidValue}"/>
									</c:when>    
									<c:otherwise>
										<c:set var = "expectedValue" value = "${sale.expectedValue}"/>
									</c:otherwise>
								</c:choose>
								
							<tbody>
								<tr>
									<td style="vertical-align:center; text-align:center;"><img src="${sale.nft.imageUrl}" alt="${sale.nft.name}"></td>
								    <td>${sale.nft.name}</td>
								    <td>${sale.nft.type}</td>
								    <td>${sale.nft.description}</td>
								    <td class="center"><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${sale.creationTime}" /></td>
								    <td class="center">${sale.type}</td>
								    <td class="center">${sale.cryptocurrency.name}</td>
								    <td class="center"><fmt:formatNumber type="number" maxFractionDigits="5" value="${expectedValue}"/></td>
								    <td class="center">
								    <c:choose>
									    <c:when test="${bidValue > 0}"><fmt:formatNumber type="number" maxFractionDigits="5" value="${bidValue}"/></c:when>    
									    <c:otherwise>-</c:otherwise>
									</c:choose>
									</td>
									<c:choose>
										<c:when test="${ highestBidId == userId }">
									    	<td class="message">You have made the highest bid so far.</td>
									    </c:when>
									    <c:when test="${bidValue > 0}">
									    	<td class="message">You no longer have the highest bidder. <span class="action_btn"><a href="/sale/cancel/bid/${bidId}">Cancel offer</a></span></td>
									    </c:when>  
									    <c:otherwise>
									        <td class="center">-</td>
									    </c:otherwise>
									</c:choose>	
									
								    <td><span class="action_btn"><a href="/sale/makeOffer/${sale.id}">Buy NFT</a></span></td>
								</tr>
							</tbody>
								
							</c:forEach>
						</table>
						
					</div>
			
		</div>
	</body>
</html>