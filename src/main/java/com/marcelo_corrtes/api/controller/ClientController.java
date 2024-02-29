package com.marcelo_corrtes.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo_corrtes.api.dtos.BalanceDTO;
import com.marcelo_corrtes.api.dtos.TransactionDTO;
import com.marcelo_corrtes.api.models.TransactionModel;
import com.marcelo_corrtes.api.service.RinhaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
@RequestMapping("/clientes")
public class ClientController {

    private final RinhaService rinhaService;

    public ClientController(RinhaService rinhaService) {
        this.rinhaService = rinhaService;
    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<BalanceDTO> save(@RequestBody TransactionDTO body, @PathVariable Long id) {

        BalanceDTO balance = rinhaService.postTransactionbyClientId(body, id);

        return ResponseEntity.status(HttpStatus.CREATED).body(balance);

    }

    @GetMapping("/{id}/extrato")
    public String getExtractById(@PathVariable Long id) {
        return new String();
    }
}
