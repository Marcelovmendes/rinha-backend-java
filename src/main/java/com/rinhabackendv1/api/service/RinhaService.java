package com.rinhabackendv1.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.rinhabackendv1.api.dtos.BalanceDTO;
import com.rinhabackendv1.api.dtos.ExtractDTO;
import com.rinhabackendv1.api.dtos.TransactionDTO;
import com.rinhabackendv1.api.exceptions.NotFoundUserException;
import com.rinhabackendv1.api.models.ClientModel;
import com.rinhabackendv1.api.models.TransactionModel;
import com.rinhabackendv1.api.repository.ClientRepository;
import com.rinhabackendv1.api.repository.TransactionsRepository;

@Service
public class RinhaService {

    private final ClientRepository clientRepository;
    private final TransactionsRepository transactionsRepository;
    private static final Logger logger = LoggerFactory.getLogger(RinhaService.class);

    RinhaService(ClientRepository clientRepository, TransactionsRepository transactionsRepository) {
        this.clientRepository = clientRepository;
        this.transactionsRepository = transactionsRepository;
    }

    public List<ClientModel> getAllClients() {
      var findall=clientRepository.findAll();
        
      logger.info("Retorno do findAll: {}", findall);
      return findall;
    }

    public BalanceDTO postTransactionbyClientId(TransactionDTO body, Long clientId) {

        ClientModel client = getClient(clientId);
        TransactionModel transaction = new TransactionModel(body, client);

        validateTransaction(transaction, client);

        transactionsRepository.save(transaction);
        clientRepository.save(client);
        
        return new BalanceDTO(client);

    }

    public ExtractDTO getExtrato(Long clientId) {
        ClientModel client = getClient(clientId);
        BalanceDTO balance =  new BalanceDTO(client);

        List<TransactionModel> transactions = transactionsRepository
                .findTop10ByClienteOrderByRealizadoEmDesc(client);

        ExtractDTO extract = new ExtractDTO();
        extract.setSaldo(balance);
        extract.setDataExtrato(LocalDateTime.now());
        extract.setUltimasTransacoes(transactions.stream().map(TransactionDTO::new).collect(Collectors.toList()));
        return extract;

    }

    private ClientModel getClient(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundUserException("User not found!"));
    }
 
    private void validateTransaction(TransactionModel transaction, ClientModel client) {
        if (transaction.getTipo().equals("c")) {
            client.setLimite(client.getLimite() - transaction.getValor());
        }
        if (transaction.getTipo().equals("d")) {
            if (client.getSaldo() - transaction.getValor() < -client.getLimite()) {
                throw new IllegalArgumentException("Balance is not enough!");
            }
            client.setSaldo(client.getSaldo() - transaction.getValor());
        }
    }

                                                                                                                        
}