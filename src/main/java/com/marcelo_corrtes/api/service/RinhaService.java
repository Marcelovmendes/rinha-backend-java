package com.marcelo_corrtes.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    @Transactional(readOnly = true)
    public TransactionDTO postTransactionbyClientId(TransactionDTO body, Long clientId) {

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
            if (client.getSaldo() + client.getLimite() > transaction.getValor()) {
                throw new IllegalArgumentException("Balance is not enough!");
            }
            client.setSaldo(client.getSaldo() - transaction.getValor());
        }
        TransactionModel savedTrasaction = transactionsRepository.save(transaction);

        return toDTO(savedTrasaction);
    }

    @Transactional(readOnly = true)
    public ExtractDTO getExtrato(Long clientId) {
        try {
            ClientModel client = clientRepository.findById(clientId).orElseThrow(NotFoundException::new);

            BalanceDTO balance = new BalanceDTO();
            balance.setTotal(client.getSaldo());
            balance.setDataExtrato(LocalDateTime.now());
            balance.setLimite(client.getLimite());

            List<TransactionModel> transactions = transactionsRepository
                    .findTop10ByClienteOrderByRealizadoEmDesc(client);
            ExtractDTO extract = new ExtractDTO();
            extract.setSaldo(balance);
            extract.setUltimasTransacoes(transactions.stream().map(this::toDTO).collect(Collectors.toList()));

            return extract;
        } catch (NotFoundException e) {

            return null;
        }
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