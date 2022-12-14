<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Welcome to NFT Trading Market Application. A perfect place to start with buying and selling NFT's with authentication.</title>
		
		<style>
			
			div {padding-top: 50px;}
			
			td { vertical-align:center; text-align:right; }
			
			#text { font-size: 14px; }
			
			#error { font-size: 13px; vertical-align:center; text-align:center; color: red; }
			
		</style>
		
	</head>
	<body>
		<div align="center" >
			<table border="0" cellpadding="5">
			
				<caption>
					<h2>NFT Market Place</h2>
				</caption>
				
				<form action="/localLogin" method="post">
				
					<tbody>
						
						<tr>
						    <td>E-mail: </td>
						    <td><input type="email" name="email" required/></td>
						</tr>
						<tr>
						    <td>Password: </td>
						    <td><input type="password" name="password" required/></td>
						</tr>
						<tr>
						    <td colspan="2"><input type="submit" value="Login" /></td>
						</tr>
						<tr>
						    <td colspan="2" id="text">New user? <a href="registerUser">Register</a> &nbsp;|&nbsp; <a href="googleLogin">Login with Google</a></td>
						</tr>
						<tr>
						    <td colspan="2" id="error">${msg}</td>
						</tr>
						
					</tbody>
					
				</form>
			</table>
		</div>
	</body>
</html>