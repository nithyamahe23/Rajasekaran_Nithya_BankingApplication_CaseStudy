package com.bankingapplication.repository;

import com.bankingapplication.model.Account;
import com.bankingapplication.model.Transaction;
import com.bankingapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<List<Transaction>> findByUser(User user);
}
