package com.capgemini.account.Account.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.account.Account.entity.Account;
import com.capgemini.account.Account.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repository;
	
	@Override
	public List<Account> getAllAccounts() {
		return repository.findAll();
	}

	@Override
	public void update(Account account) {
		repository.save(account);
	}

	@Override
	public Optional<Account> getAccountById(int accountNumber) {
		return repository.findById(accountNumber);
	}

	@Override
	public Optional<Account> findById(int accountNumber) {
		return repository.findById(accountNumber);
	}
}
