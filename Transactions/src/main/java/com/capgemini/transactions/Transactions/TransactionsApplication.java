package com.capgemini.transactions.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.capgemini.transactions.Transactions.Entity.Transaction;
import com.capgemini.transactions.Transactions.Entity.TransactionType;
import com.capgemini.transactions.Transactions.repository.TransactionRepository;
import com.capgemini.transactions.Transactions.service.TransactionService;

@EnableDiscoveryClient
@SpringBootApplication
public class TransactionsApplication {

	@Autowired
	TransactionService service;
	
	public static void main(String[] args) {
		SpringApplication.run(TransactionsApplication.class, args);
	}

	
	  @Bean
	public CommandLineRunner loadinitialdata(TransactionRepository repository) {
		return (arg) -> {
			repository.save(new Transaction(101,5000.0, TransactionType.DEPOSIT,"ATM"));
			service.deposit(101,10000.00,2000.00,"ATM");
			service.deposit(102,70000.00,8900.00,"PAYTM");
			service.deposit(103,50000.00,5600.00,"KIK");
			service.deposit(104,30000.00,1400.00,"XYZ");
			service.deposit(105,80000.00,1000.00,"ATM");
			
		};
	}

	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}
	
}

