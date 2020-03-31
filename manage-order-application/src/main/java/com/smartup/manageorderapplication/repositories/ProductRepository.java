package com.smartup.manageorderapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartup.manageorderapplication.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
