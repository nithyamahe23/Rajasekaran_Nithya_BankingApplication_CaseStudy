package com.bankingapplication.repository;

import com.bankingapplication.model.Account;
import com.bankingapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<List<Account>> findByUser(User user);
}
