package com.marcelo_corrtes.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo_corrtes.api.dtos.ExtractDTO;
import com.marcelo_corrtes.api.dtos.TransactionDTO;
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
        try {
            ClientModel cliente = clientRepository.findById(clientId).orElseThrow(NotFoundException::new);
            TransactionModel transacao = new TransactionModel();
            transacao.setValor(body.getValor());
            transacao.setTipo(body.getTipo());
            transacao.setDescricao(body.getDescricao());
            transacao.setRealizadoEm(transacao.getRealizadoEm());

            transacao.setCliente(cliente);

            TransactionModel savedTrasaction = transactionsRepository.save(transacao);

            return toDTO(savedTrasaction);

        } catch (Exception e) {
            return null;
        }

    }

    @Transactional(readOnly = true)
    public ExtractDTO getExtrato(Long clientId) {
        try {
            ClientModel cliente = clientRepository.findById(clientId).orElseThrow(NotFoundException::new);

            BalanceDTO saldo = new BalanceDTO();
            saldo.setTotal(cliente.getSaldo());
            saldo.setDataExtrato(LocalDateTime.now());
            saldo.setLimite(cliente.getLimite());

            List<TransactionModel> transacoes = transactionsRepository
                    .findTop10ByClienteOrderByRealizadoEmDesc(cliente);
            ExtractDTO extrato = new ExtractDTO();
            extrato.setSaldo(saldo);
            extrato.setUltimasTransacoes(transacoes.stream().map(this::toDTO).collect(Collectors.toList()));

            return extrato;
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