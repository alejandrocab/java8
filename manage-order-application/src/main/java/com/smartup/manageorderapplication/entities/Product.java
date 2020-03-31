package com.smartup.manageorderapplication.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "brand", nullable = false)
	private String brand;
	@Column(name = "model", nullable = true)
	private String model;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
	private Size size;
	@Column(name = "weight", nullable = true)
	private Double weight;
	@Column(name = "price", nullable = false)
	private Double price;
	@Column(name = "iva", nullable = false)
	private Double iva;
	
	
	@Column(name = "ts", nullable = false)
	private Timestamp ts;
	
	@PrePersist
	@PreUpdate
	public void updateTs() {
		ts = new Timestamp(System.currentTimeMillis());
	}
}
