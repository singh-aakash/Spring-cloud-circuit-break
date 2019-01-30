package com.capgemini.account.Account.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.account.Account.entity.Account;

public interface AccountService {

	List<Account> getAllAccounts();

	void update(Account account);

	Optional<Account> getAccountById(int accountNumber);

	Optional<Account> findById(int accountNumber);

}