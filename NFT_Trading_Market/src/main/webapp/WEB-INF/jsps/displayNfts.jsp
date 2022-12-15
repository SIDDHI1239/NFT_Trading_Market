<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Display NFT's</title>
		
		<link href="/css/style.css" rel="stylesheet" type="text/css">
		<link href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
		
		<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
		<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
		
		<script>
			$(document).ready(function () {
			    $('#nft_table').DataTable({
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
				<a href="/profile">Back to Profile</a> | <a href="/logout" onclick="return confirm('Do you want to logout?');">Logout</a>
			</div>
			<br>
							
				<div>
					<table border="0" cellpadding="5" id="nft_table" style="width:100%">
						<caption>
							<h2>My NFT's</h2>
							<h3>${msg}</h3>
						</caption>
						<thead>
						<tr>
							<th></th>
							<th>Name</th>
							<th>Type</th>
							<th>Description</th>
							<th>Asset URL</th>
							<th></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${nfts}" var="nft">
							<tr>
								<td style="vertical-align:center; text-align:center;"><img src="${nft.imageUrl}" alt="${nft.name}"></td>
								<td>${nft.name}</td>
								<td>${nft.type}</td>
								<td>${nft.description}</td>
								<td>${nft.assetUrl}</td>
								<td><span class="action_btn"><a href="/sale/new/${nft.tokenId}">Sell NFT</a></span></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
		</div>
	</body>
</html>