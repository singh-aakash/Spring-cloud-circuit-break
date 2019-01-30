package com.capgemini.account.Account;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.capgemini.account.Account.entity.CurrentAccount;
import com.capgemini.account.Account.entity.SavingsAccount;
import com.capgemini.account.Account.repository.AccountRepository;
@EnableDiscoveryClient
@SpringBootApplication
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(AccountRepository repository) {
		return (arg) -> {
			repository.save(new SavingsAccount(101,"Aakash", 10000.00,true));
			repository.save(new CurrentAccount(102,"Saket", 20000.00,5000.00));
			repository.save(new SavingsAccount(103,"Shivam", 30000.00,true));
			
		};
	}
}

