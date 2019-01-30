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

	<table>

		<tr>
			<th>transaction_id</th>
			<th>account_number</th>
			<th>amount</th>
			<th>current_balance</th>
			<th>transaction_date</th>
			<th>transaction_details</th>
			<th>transaction_type</th>
		</tr>

		<details:forEach var="account" items="${currentDataSet.transactions}">
			<details:if test="${currentDataSet.transactions!=null}">
				<tr>
					<td>${account.transactionId}</td>
					<td>${account.accountNumber}</td>
					<td>${account.amount}</td>
					<td>${account.currentBalance}</td>
					<td>${account.transactionDate}</td>
					<td>${account.transactionDetails}</td>
					<td>${account.transactionType}</td>
				</tr>
			</details:if>
		</details:forEach>

	</table>

	<a href="${currentDataSet.previousLink.href}">Previous</a> |
	<a href="${currentDataSet.nextLink.href}">Next</a>
</body>
</html>