<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Display offers</title>
		
		<link href="/css/style.css" rel="stylesheet" type="text/css">
		<link href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
		
		<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
		<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
		
		<style>
		
			#table_content { max-width: 700px }
		
		</style>
		
		<script>
			$(document).ready(function () {
			    $('#bids_table').DataTable({
			    	ordering: false,
			    	searching: false,
			        info: false,
			        order: [[1, 'desc']],
			    });
			});
		</script>
	</head>
	<body>
		<div align="center" id="main">
			<div align="right">
				<a href="/profile">Back to Profile</a> | <a href="/logout">Logout</a>
			</div>
			</br>
					
					<div align="center" >
						<h3><c:out value="${msg}"/></h3>
					</div>
					</br>
					<div id="table_content">
						<table id="bids_table" border="0" cellpadding="5" style="width:100%" >
							<caption>
								<h2>Received offers</h2>
							</caption>
							<thead>
								<tr>
									<th>Bidder's name</th>
									<th>Bid time</th>
									<th>Bid value</th>
									<th>Bid expiration</th>
									<th>Status</th>
									<th>Accept offer</th>
								</tr>
							</thead>
							
							<jsp:useBean id="now" class="java.util.Date"/>
							
							<c:forEach items="${bids}" var="bid">
							
								<c:set var = "acceptOffer" value = "-"/>
							
								<c:choose>
									<c:when test="${bid.wasAccepted}">
										<c:set var = "status" value = "Winner"/>
									</c:when>
									<c:when test="${bid.id == highestBid.id}">
										<c:set var = "status" value = "Highest bid"/>
										<c:set var = "acceptOffer" value = "<span class='action_btn'><a href='/sale/acceptOffer/${bid.id}'>Accept Offer</a></span>"/>
									</c:when>
									<c:when test="${bid.expirationTime gt now}">
										<c:set var = "status" value = "Active"/>
									</c:when>
									<c:otherwise>
										<c:set var = "status" value = "Expired"/>
									</c:otherwise>
								</c:choose>
								
								<tr>
									<td>${bid.user.nickName}</td>
								    <td class="center"><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${bid.bidTime}" /></td>
									<td class="center"><fmt:formatNumber type="number" maxFractionDigits="5" value="${bid.bidValue}"/></td>
								    <td class="center"><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${bid.expirationTime}" /></td>
								    <td class="center">${status}</td>
								    <td class="center">${acceptOffer}</td>
								</tr>
							</c:forEach>
						</table>
					</div>

		</div>
	</body>
</html>