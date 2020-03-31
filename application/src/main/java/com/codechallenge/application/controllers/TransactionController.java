package com.codechallenge.application.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codechallenge.application.dto.TransactionDto;
import com.codechallenge.application.dto.TransactionStatusDto;
import com.codechallenge.application.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	private TransactionService service;
	
	public TransactionController(TransactionService service) {
		this.service=service;
	}
	
	@GetMapping
	public ResponseEntity<List<TransactionDto>> getTransactions (
			@RequestParam(name="accountIban", required=false)String accountIban,
			@RequestParam(name="order", required=false)String order){
		List<TransactionDto> response = service.getTransactions(accountIban, order);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<TransactionDto> createTransaction (@RequestBody @Valid TransactionDto inDto){
		TransactionDto response = service.createTransaction(inDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/status")
	public ResponseEntity<TransactionStatusDto> statusTransaction (@RequestBody @Valid TransactionStatusDto inDto){
		TransactionStatusDto response =  service.statusTransaction(inDto);
		return ResponseEntity.ok(response);
	}
}
