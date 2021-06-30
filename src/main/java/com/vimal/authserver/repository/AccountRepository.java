package com.vimal.authserver.repository;

import com.vimal.authserver.config.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    public Optional<Account> findByUserName(String userName);
}
