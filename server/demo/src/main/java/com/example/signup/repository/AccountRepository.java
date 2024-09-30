package com.example.signup.repository;

import com.example.signup.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface    AccountRepository extends JpaRepository<Account, Long> {
    // Custom query methods can be defined here
}
