package com.smartup.manageorderapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartup.manageorderapplication.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
