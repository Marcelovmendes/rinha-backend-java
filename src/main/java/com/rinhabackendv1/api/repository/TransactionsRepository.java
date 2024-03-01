package com.rinhabackendv1.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rinhabackendv1.api.models.ClientModel;
import com.rinhabackendv1.api.models.TransactionModel;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionModel, Long> {

    List<TransactionModel> findTop10ByClienteOrderByRealizadoEmDesc(ClientModel cliente);
}
