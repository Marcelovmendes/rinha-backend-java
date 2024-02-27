package com.marcelo_corrtes.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
