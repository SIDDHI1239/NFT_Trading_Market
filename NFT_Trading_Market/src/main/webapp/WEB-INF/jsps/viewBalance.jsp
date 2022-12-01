<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Wallet Balance</title>
</head>
<body>
<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>Wallet Balance</h2>
			</caption>
			<tr>
				<th>CryptoCurrency</th>
				<th>Balance</th>
			</tr>
			<c:forEach items="${wallets}" var="wallet">
				<tr>
					<td><c:out value="${wallet.walletId.cryptocurrency.symbol}" /></td>
					<td><c:out value="${wallet.balance}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
	<div align="center">
		<a href="logout">Logout</a>
	</div>
</body>
</html>