package com.marcelo_corrtes.api.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo_corrtes.api.dtos.BalanceDTO;
import com.marcelo_corrtes.api.dtos.ExtractDTO;
import com.marcelo_corrtes.api.dtos.TransactionDTO;
import com.marcelo_corrtes.api.service.RinhaService;

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

    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.status(HttpStatus.OK).body("It's healthy!");
    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<BalanceDTO> save(@RequestBody TransactionDTO body, @PathVariable @Valid Long id) {

        BalanceDTO balance = rinhaService.postTransactionbyClientId(body, id);

        return ResponseEntity.status(HttpStatus.CREATED).body(balance);

    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<Optional<ExtractDTO>> getExtractById(@PathVariable @Valid Long id) {

        ExtractDTO extract = rinhaService.getExtrato(id);
        Optional<ExtractDTO> extractOptional = Optional.ofNullable(extract);

        if (extractOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(extractOptional);
        }

        return ResponseEntity.status(HttpStatus.OK).body(Optional.ofNullable(extract));

    }
}
