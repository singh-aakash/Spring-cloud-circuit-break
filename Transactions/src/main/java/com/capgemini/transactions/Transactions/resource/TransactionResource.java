package com.capgemini.transactions.Transactions.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capgemini.transactions.Transactions.Entity.Transaction;
import com.capgemini.transactions.Transactions.service.TransactionService;


@RestController
@RequestMapping("/transactions")
public class TransactionResource {

	@Autowired
	private TransactionService service;
	@Autowired
	RestTemplate temp;

	@PostMapping
	public ResponseEntity<Transaction> deposit(@RequestBody Transaction transaction) {
		ResponseEntity<Double> entity = temp.getForEntity(
				"http://accountProperties/accounts/" + transaction.getAccountNumber() + "/balance", Double.class);
		Double currentBalance = entity.getBody();
		Double updateBalance = service.deposit(transaction.getAccountNumber(), transaction.getAmount(), currentBalance,
				"deposit");
		temp.put(
				"http://accountProperties/accounts/" + transaction.getAccountNumber() + "?currentBalance=" + updateBalance,
				null);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/withdraw")
	public ResponseEntity<Transaction> withdraw(@RequestBody Transaction transaction) {

		ResponseEntity<Double> entity = temp.getForEntity(
				"http://accountProperties/accounts/" + transaction.getAccountNumber() + "/balance", Double.class);
		Double currentBalance = entity.getBody();
		Double updateBalance = service.withdraw(transaction.getAccountNumber(), transaction.getAmount(), currentBalance,
				"withdraw");
		temp.put(
				"http://accountProperties/accounts/" + transaction.getAccountNumber() + "?currentBalance=" + updateBalance,
				null);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
	
	@GetMapping("/statement")
	public ResponseEntity<CurrentDataSet> getStatement(){
		CurrentDataSet currentDataSet = new CurrentDataSet();
		List<Transaction> transactions = service.getStatement();
		currentDataSet.setTransactions(transactions);
		return new ResponseEntity<CurrentDataSet>(currentDataSet,HttpStatus.OK);
	}
}
