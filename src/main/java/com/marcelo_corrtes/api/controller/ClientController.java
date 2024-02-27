package com.marcelo_corrtes.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo_corrtes.api.dtos.TransactionDTO;
import com.marcelo_corrtes.api.models.TransactionModel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/clientes")
public class ClientController {

    @GetMapping
    public String getListClient() {

        return "Lista de Clientes";

    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<TransactionModel> createTransaction(@PathVariable Long id,
            @RequestBody @Valid TransactionDTO entity) {

        return ResponseEntity.ok(new TransactionModel());
    }

    @GetMapping("/{id}/extrato")
    public String getExtractById(@PathVariable Long id) {
        return new String();
    }

}
