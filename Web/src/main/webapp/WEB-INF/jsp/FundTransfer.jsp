<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>${message}</h1>
<form action="fundTransfer" >
	Enter Sender Account Number: <input type="number" name="senderAccountNumber" required="required"/><br/>
	Enter Receiver Account Number: <input type="number" name="receiverAccountNumber" required="required"/><br/>
	Enter Amount : <input type="number" name="amount" required="required"/><br/>
	<input type="submit" name="submit"/><input type="reset" name="reset" value="Reset">
</form>
</body>
</html>