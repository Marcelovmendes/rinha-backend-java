package com.marcelo_corrtes.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo_corrtes.api.dtos.TransactionDTO;
import com.marcelo_corrtes.api.repository.ClientRepository;
import com.marcelo_corrtes.api.repository.TransactionsRepository;

@Service
public class RinhaService {

    final ClientRepository clientRepository;
    final TransactionsRepository transactionsRepository;

    RinhaService(ClientRepository clientRepository, TransactionsRepository transactionsRepository) {
        this.clientRepository = clientRepository;
        this.transactionsRepository = transactionsRepository;
    }

    public String postTransactionbyClientId(TransactionDTO entity) {
        /*
         * Uma transação de débito nunca pode deixar o saldo do cliente
         * menor que seu limite disponível. Por exemplo, um cliente com
         * limite de 1000 (R$ 10) nunca deverá ter o saldo menor que -1000
         * (R$ -10). Nesse caso, um saldo de -1001 ou menor significa inconsistência
         * na Rinha de Backend!
         */

        return "response 200 ok";

    }
}
