<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create NFT</title>
</head>
<body>

<div align="center">
	<h2>Create NFT</h2><br/>
	<form action="createNft" method="post">
		Name : <input type="text" name="name" /><br/>
		Type : <input type="text" name="type" /><br/>
		Description : <input type="text" name="description" /><br/>
		Image URL : <input type="text" name="imageUrl" /><br/>
		Asset URL : <input type="text" name="assetUrl" /><br/>
		<input type="submit" value="Create" />
	</form>
</div>

</body>
</html>