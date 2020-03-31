package com.smartup.manageorderapplication.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartup.manageorderapplication.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	public List<Order> findByClientIdOrderByOrderDate (Long idClient);
	public List<Order> findByClientIdAndOrderDateBetweenOrderByOrderDate (Long idCliente, LocalDateTime dateOne, LocalDateTime dateTwo); 
}
