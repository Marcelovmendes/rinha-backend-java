package com.marcelo_corrtes.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo_corrtes.api.dtos.TransactionDTO;
import com.marcelo_corrtes.api.models.ClientModel;
import com.marcelo_corrtes.api.models.TransactionModel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@RestController()
@RequestMapping("/clientes")
public class ClientController {

    @GetMapping
    public String GetListClient() {

        return "Lista de Clientes";

    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<Object> CreateTransaction(@PathVariable Long id,
            @RequestBody @Valid TransactionDTO entity) {

        return entity;
    }

    @GetMapping("/{id}/extrato")
    public String getExtractById(@PathVariable Long id) {
        return new String();
    }

}
