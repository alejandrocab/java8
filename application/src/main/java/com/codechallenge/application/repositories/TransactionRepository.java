package com.codechallenge.application.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codechallenge.application.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

	public List<Transaction> findByAccountAccountIbanLike(String accountIban, Sort sort);
	public List<Transaction> findAll();
}
