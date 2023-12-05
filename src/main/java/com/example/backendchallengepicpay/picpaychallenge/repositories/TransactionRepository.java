package com.example.backendchallengepicpay.picpaychallenge.repositories;

import com.example.backendchallengepicpay.picpaychallenge.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
