<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	 <%@ taglib prefix="details" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>
		<p>Deposit Form</p>
	</h1>
	<h1>${message}</h1>
	<form action="depositMoney" method="get">
		Enter Account Number: <input name="accountNumber" /><br /> Enter
		Amount : <input name="amount" /><br /> <input type="submit" />
		
		<a href="getStatements?offset=1&size=2">GET STATEMENTS</a>
	</form>
	<table>

	<tr>
		<th>transaction_id </th>
		<th>account_number </th>		
		<th>amount </th>
		<th>current_balance </th>
		<th>transaction_date </th>
		<th>transaction_details </th>
		<th>transaction_type</th>
	</tr>
	
	<details:forEach var="account" items="${currentDataSet.transactions}">
	<tr>
		<td>${account.transactionId}</td>
		<td>${account.accountNumber}</td>
		<td>${account.amount}</td>
		<td>${account.currentBalance}</td>
		<td>${account.transactionDate}</td>
		<td>${account.transactionDetails}</td>
		<td>${account.transactionType}</td>
	</tr>
	</details:forEach>
	
</table>

<a href="${currentDataSet.previousLink.href}">Previous</a> | <a href="${currentDataSet.nextLink.href}">Next</a>
	
</body>
</html>