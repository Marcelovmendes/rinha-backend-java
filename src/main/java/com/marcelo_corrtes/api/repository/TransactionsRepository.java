package com.marcelo_corrtes.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcelo_corrtes.api.models.ClientModel;
import com.marcelo_corrtes.api.models.TransactionModel;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionModel, Long> {

    List<TransactionModel> findTop10ByClienteOrderByRealizadoEmDesc(ClientModel cliente);
}
