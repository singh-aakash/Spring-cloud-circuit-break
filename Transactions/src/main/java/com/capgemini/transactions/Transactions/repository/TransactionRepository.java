package com.capgemini.transactions.Transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.transactions.Transactions.Entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}