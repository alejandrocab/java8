package com.smartup.manageorderapplication.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.smartup.manageorderapplication.utils.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "postalCode", nullable = false)
	private Integer postalCode;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	@Column(name = "shippingCity", nullable = false)
	private String shippingCity;
	@Column(name = "orderDate", nullable = false)
	private LocalDateTime orderDate;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ItemOrder> items;
	@ManyToOne(fetch = FetchType.EAGER)
	private Client client;
	
	@PrePersist
	@PreUpdate
	public void updateOrderDate() {
		orderDate = LocalDateTime.now();
	}	
}
