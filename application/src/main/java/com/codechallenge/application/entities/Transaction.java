package com.codechallenge.application.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private String reference;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="account")
	private Account account;
	
	@Column(name = "date", nullable = false)
	private LocalDateTime date;
	@Column(name = "amount", nullable = false)
	private Double amount;
	@Column(name = "fee", nullable = false)
	private Double fee;
	@Column(name = "description", nullable = true)
	private String description;
}
