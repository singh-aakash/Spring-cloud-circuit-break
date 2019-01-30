		package com.capgemini.transactions.Transactions.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.transactions.Transactions.Entity.Transaction;
import com.capgemini.transactions.Transactions.Entity.TransactionType;
import com.capgemini.transactions.Transactions.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository repo;
		
	
	 public double deposit(int accountNumber, double amount, double currentBalance, String transactionDetails) {
		 
		 Transaction transaction =new Transaction();
		 transaction.setAccountNumber(accountNumber);
		 currentBalance = currentBalance + amount;
		 transaction.setCurrentBalance(currentBalance);
		 transaction.setTransactionDetails(transactionDetails);
		 transaction.setTransactionType(TransactionType.DEPOSIT);
		 transaction.setTransactionDate(LocalDateTime.now());
		 repo.save(transaction);
		 return currentBalance;
	 }


	@Override
	public Double withdraw(Integer accountNumber, Double amount, Double currentBalance, String transactionDetails) {

		 
		 Transaction transaction =new Transaction();
		 transaction.setAccountNumber(accountNumber);
		 currentBalance = currentBalance - amount;
		 transaction.setCurrentBalance(currentBalance);
		 transaction.setTransactionDetails(transactionDetails);
		 transaction.setTransactionType(TransactionType.WITHDRAW);
		 transaction.setTransactionDate(LocalDateTime.now());
		 repo.save(transaction);
		 return currentBalance;
	}


	@Override
	public List<Transaction> getStatement() {
		return repo.findAll();
	}
	 
}
