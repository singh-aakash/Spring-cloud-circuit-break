package com.capgemini.account.Account.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.account.Account.entity.Account;
import com.capgemini.account.Account.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountResource {

	@Autowired
	private AccountService service;
	
	@GetMapping
	public List<Account> getAllAccount() {
		List<Account> accounts = service.getAllAccounts();
		return accounts;
		
	}
	
	@PutMapping("/{accountNumber}")
	public void update(@PathVariable int accountNumber, @RequestParam("currentBalance") double currentBalance) {
		Account account = service.findById(accountNumber).get();
		account.setCurrentBalance(currentBalance);
		service.update(account);
	}

	@GetMapping("/{accountNumber}")
	public Optional<Account> getAccountById(@PathVariable int accountNumber) {
		Optional<Account> account = service.getAccountById(accountNumber);
		return account;
	}
	
	@GetMapping("/{accountNumber}/balance")
	public double getCurrentBalance(@PathVariable int accountNumber) {

		double currentBalance = service.findById(accountNumber).get().getCurrentBalance();
		return currentBalance;
	}

}
