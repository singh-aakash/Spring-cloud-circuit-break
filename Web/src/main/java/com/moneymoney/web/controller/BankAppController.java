package com.moneymoney.web.controller;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.moneymoney.web.entity.Transaction;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

//@RefreshScope
@Controller
public class BankAppController {
	
	@Autowired
	
	private RestTemplate restTemplate;
	@RequestMapping("/")
	public String homePage() {
		return "index";
	}

	
	@RequestMapping("/deposit")
	public String depositForm() {
		return "DepositForm";
	}
	
	@HystrixCommand(fallbackMethod = "breaker")
	@RequestMapping("/depositMoney")
	public String deposit(@ModelAttribute Transaction transaction,
			Model model) {
		System.out.println("hello");
		//transaction.setTransactionType("deposit");
		restTemplate.postForEntity("http://zuul/Transactions/transactions", transaction, null);
	
		model.addAttribute("message","Successfully deposites!");
		return "DepositForm";
	}
	
	public String breaker(@ModelAttribute Transaction transaction, Model model) {
		model.addAttribute("message", "Transaction can't be completed right now!");
		return "DepositForm";
	}
	
	@RequestMapping("/withdraw")
	public String withdrawForm() {
		return "WithdrawForm";
	}
	
	@HystrixCommand(fallbackMethod = "breaker")
	@RequestMapping("/withdrawMoney")
	public String withdraw(@ModelAttribute Transaction transaction,
			Model model) {
		restTemplate.postForEntity("http://zuul/Transactions/transactions/withdraw", transaction, null);
	
		model.addAttribute("message","Successfully withdraw!");
		return "WithdrawForm";
	}
	
	@RequestMapping("/moneyTransfer")
	public String fundTransferForm() {
		return "FundTransfer";
	}
	
	@HystrixCommand(fallbackMethod = "TransferFundbreaker")
	@RequestMapping("/fundTransfer")
	public String fundTransfer(@RequestParam("senderAccountNumber") int senderAccountNumber,
			@RequestParam("receiverAccountNumber") int ReceiverAccountNumber,
			@RequestParam("amount") double amount, @ModelAttribute Transaction transaction,
			Model model) {
		transaction.setAccountNumber(senderAccountNumber);
		System.out.println("hello");
		restTemplate.postForEntity("http://zuul/Transactions/transactions/withdraw", transaction, null);
		transaction.setAccountNumber(ReceiverAccountNumber);
		restTemplate.postForEntity("http://zuul/Transactions/transactions", transaction, null);
		model.addAttribute("message","Successfully Transfered!");
		return "FundTransfer";
	}
	public String TransferFundbreaker(@ModelAttribute Transaction transaction, Model model) {
		model.addAttribute("message", "Transaction can't be completed right now!");
		return "FundTransfer";
	}
	
	@RequestMapping("/getStatements")
	public ModelAndView getStatementFundTransfer(@RequestParam("offset") int offset, @RequestParam("size") int size) {
		
		CurrentDataSet currentDataSet = restTemplate.getForObject("http://zuul/Transactions/transactions/statement", CurrentDataSet.class);
		int currentSize=size==0?4:size;
		int currentOffset=offset==0?1:offset;
		Link next=linkTo(methodOn(BankAppController.class).getStatementFundTransfer(currentOffset+currentSize,currentSize)).withRel("next");
		Link previous=linkTo(methodOn(BankAppController.class).getStatementFundTransfer(currentOffset-currentOffset,currentSize)).withRel("previous");
		List<Transaction> currentDataSetList = new ArrayList<Transaction>();
		List<Transaction> transactions = currentDataSet.getTransactions();
		for (int i = currentOffset - 1; i < currentSize + currentOffset - 1; i++) { 
			
			Transaction transaction = transactions.get(i);
			currentDataSetList.add(transaction);
			}
		CurrentDataSet dataSet = new CurrentDataSet(currentDataSetList, next, previous);

		return new ModelAndView("DepositForm","currentDataSet",dataSet);
	}
}
