package com.marcelo_corrtes.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo_corrtes.api.dtos.ExtractDTO;
import com.marcelo_corrtes.api.dtos.TransactionDTO;
import com.marcelo_corrtes.api.exceptions.NotFoundUserException;
import com.marcelo_corrtes.api.models.ClientModel;
import com.marcelo_corrtes.api.models.TransactionModel;
import com.marcelo_corrtes.api.repository.ClientRepository;
import com.marcelo_corrtes.api.repository.TransactionsRepository;
import com.marcelo_corrtes.api.dtos.BalanceDTO;

@Service
public class RinhaService {

    final ClientRepository clientRepository;
    final TransactionsRepository transactionsRepository;

    RinhaService(ClientRepository clientRepository, TransactionsRepository transactionsRepository) {
        this.clientRepository = clientRepository;
        this.transactionsRepository = transactionsRepository;
    }

    public BalanceDTO postTransactionbyClientId(TransactionDTO body, Long clientId) {

        ClientModel client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundUserException("User not found!"));

        TransactionModel transaction = new TransactionModel();
        transaction.setValor(body.getValor());
        transaction.setTipo(body.getTipo());
        transaction.setDescricao(body.getDescricao());
        transaction.setRealizadoEm(transaction.getRealizadoEm());
        transaction.setCliente(client);

        if (transaction.getTipo().equals("c")) {
            client.setLimite(client.getLimite() - transaction.getValor());
        }
        if (transaction.getTipo().equals("d")) {
            if (client.getSaldo() - transaction.getValor() < -client.getLimite()) {
                throw new IllegalArgumentException("Balance is not enough!");
            }
            client.setSaldo(client.getSaldo() - transaction.getValor());
        }
        transactionsRepository.save(transaction);
        clientRepository.save(client);
        BalanceDTO balance = new BalanceDTO();
        balance.setSaldo(client.getSaldo());
        balance.setLimite(client.getLimite());

        return balance;
    }

    public ExtractDTO getExtrato(Long clientId) {
        ClientModel client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundUserException("User not found!"));

        BalanceDTO balance = new BalanceDTO();
        balance.setSaldo(client.getSaldo());
        balance.setLimite(client.getLimite());

        List<TransactionModel> transactions = transactionsRepository
                .findTop10ByClienteOrderByRealizadoEmDesc(client);
        ExtractDTO extract = new ExtractDTO();
        extract.setSaldo(balance);
        extract.setDataExtrato(LocalDateTime.now());
        extract.setUltimasTransacoes(transactions.stream().map(this::toDTO).collect(Collectors.toList()));

        return extract;

    }

    private TransactionDTO toDTO(TransactionModel transacao) {
        TransactionDTO dto = new TransactionDTO();

        dto.setValor(transacao.getValor());
        dto.setTipo(transacao.getTipo());
        dto.setDescricao(transacao.getDescricao());
        dto.setRealizadaEm(transacao.getRealizadoEm());
        return dto;
    }
}