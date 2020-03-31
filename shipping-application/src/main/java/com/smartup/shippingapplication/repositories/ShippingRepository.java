package com.smartup.shippingapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartup.shippingapplication.entities.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {

}
