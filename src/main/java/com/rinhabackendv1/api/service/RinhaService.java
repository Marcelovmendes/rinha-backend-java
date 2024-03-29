package com.rinhabackendv1.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rinhabackendv1.api.dtos.BalanceDTO;
import com.rinhabackendv1.api.dtos.ExtractDTO;
import com.rinhabackendv1.api.dtos.TransactionDTO;
import com.rinhabackendv1.api.exceptions.BalanceLimitExceededException;
import com.rinhabackendv1.api.exceptions.NotFoundUserException;
import com.rinhabackendv1.api.models.ClientModel;
import com.rinhabackendv1.api.models.TransactionModel;
import com.rinhabackendv1.api.repository.ClientRepository;
import com.rinhabackendv1.api.repository.TransactionsRepository;

import jakarta.transaction.Transactional;

@Service
public class RinhaService {

    private final ClientRepository clientRepository;
    private final TransactionsRepository transactionsRepository;

    RinhaService(ClientRepository clientRepository, TransactionsRepository transactionsRepository) {
        this.clientRepository = clientRepository;
        this.transactionsRepository = transactionsRepository;
    }

    public List<ClientModel> getAllClients() {
        return clientRepository.findAll();
    }
    @Transactional
    public BalanceDTO postTransactionbyClientId(TransactionDTO body, Long clientId) {
                                    
        if (body.getDescricao().length() > 10) {
            throw new IllegalArgumentException("Description is too long!");
        }
        if (!body.getTipo().equals("c") && !body.getTipo().equals("d")) {
            throw new IllegalArgumentException("Invalid transaction type!");
        }
        if (body.getValor() != (int) body.getValor()) {
            throw new IllegalArgumentException("Invalid value!");
        }

        ClientModel client = getClient(clientId);
        TransactionModel transaction = new TransactionModel();
        transaction.setValor(body.getValor());
        transaction.setTipo(body.getTipo());
        transaction.setDescricao(body.getDescricao());
        transaction.setRealizadoEm(LocalDateTime.now());
        transaction.setCliente(client);

        if (transaction.getTipo().equals("c")) {
            client.setSaldo(client.getSaldo() + transaction.getValor());
        }
        if (transaction.getTipo().equals("d")) {
            if (client.getSaldo() - transaction.getValor() < -client.getLimite()) {
                throw new BalanceLimitExceededException("Balance is not enough!");
            }
            client.setSaldo(client.getSaldo() - transaction.getValor());
        }
        transactionsRepository.save(transaction);
        clientRepository.save(client);
        return createBalanceDTO(client);

    }

    public ExtractDTO getExtrato(Long clientId) {
        ClientModel client = getClient(clientId);
        BalanceDTO balance = createBalanceDTO(client);

        List<TransactionModel> transactions = transactionsRepository
                .findTop10ByClienteOrderByRealizadoEmDesc(client);

        ExtractDTO extract = new ExtractDTO();
        extract.setSaldo(balance);
        extract.setDataExtrato(LocalDateTime.now());
        extract.setUltimasTransacoes(transactions.stream().map(TransactionModel::toDTO).collect(Collectors.toList()));

        return extract;

    }

    private ClientModel getClient(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundUserException("User not found!"));
    }

    private BalanceDTO createBalanceDTO(ClientModel client) {
        BalanceDTO balance = new BalanceDTO();
        balance.setSaldo(client.getSaldo());
        balance.setLimite(client.getLimite());
        return balance;
    }
}