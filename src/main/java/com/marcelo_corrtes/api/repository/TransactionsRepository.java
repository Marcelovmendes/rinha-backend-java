package com.marcelo_corrtes.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo_corrtes.api.models.TransactionModel;

public interface TransactionsRepository extends JpaRepository<TransactionModel, Long> {

}
