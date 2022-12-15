<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Withdraw Currency from Wallet</title>
		
		<link href="/css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div align="center" id="main">
			<div align="right">
				<a href="/profile">Back to Profile</a> | <a href="/logout" onclick="return confirm('Do you want to logout?');">Logout</a>
			</div>
			<br>
			<div align="center">
				<form action="updateBalance" method="post">
					<h2>Withdraw from Wallet :</h2>
					<input type="number" step=".000001" min="0" name="balanceToWithdrawOrDeposit" />
					<input type="hidden" name="walletId" value="${walletId}" />
					<input type="hidden" name="symbol" value="${symbol}" />
					<input type="hidden" name="action" value="withdraw" />
					<input type="submit" value="Withdraw" />
				</form>
			</div>
		</div>
	</body>
</html>