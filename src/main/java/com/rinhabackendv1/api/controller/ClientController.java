package com.rinhabackendv1.api.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rinhabackendv1.api.dtos.BalanceDTO;
import com.rinhabackendv1.api.dtos.ExtractDTO;
import com.rinhabackendv1.api.dtos.TransactionDTO;
import com.rinhabackendv1.api.models.ClientModel;
import com.rinhabackendv1.api.service.RinhaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
@RequestMapping("/clientes")
public class ClientController {

    private final RinhaService rinhaService;

    public ClientController(RinhaService rinhaService) {
        this.rinhaService = rinhaService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.status(HttpStatus.OK).body("It's healthy!");
    }

    @GetMapping
    public ResponseEntity<Iterable<ClientModel>> getAllClients() {
        return ResponseEntity.status(HttpStatus.OK).body(rinhaService.getAllClients());
    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<BalanceDTO> save(@RequestBody TransactionDTO body, @PathVariable @Valid Long id) {

        BalanceDTO balance = rinhaService.postTransactionbyClientId(body, id);

        return ResponseEntity.status(HttpStatus.OK).body(balance);

    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<Optional<ExtractDTO>> getExtractById(@PathVariable @Valid Long id) {

        ExtractDTO extract = rinhaService.getExtrato(id);

        return ResponseEntity.status(HttpStatus.OK).body(Optional.ofNullable(extract));

    }
}
