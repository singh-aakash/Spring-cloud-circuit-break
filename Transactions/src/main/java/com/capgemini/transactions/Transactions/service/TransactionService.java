package com.capgemini.transactions.Transactions.service;

import java.util.List;

import com.capgemini.transactions.Transactions.Entity.Transaction;

public interface TransactionService {
	 double deposit(int accountNumber, double amount, double currentBalance, String transactionDetails);

	Double withdraw(Integer accountNumber, Double amount, Double currentBalance, String string);

	List<Transaction> getStatement();
}