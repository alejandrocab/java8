package com.codechallenge.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codechallenge.application.entities.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
