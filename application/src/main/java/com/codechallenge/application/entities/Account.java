package com.codechallenge.application.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "accountIban", nullable = false)
	private String accountIban;
	
	@Column(name = "totalAmount", nullable = false)
	private Double totalAmount;
}
