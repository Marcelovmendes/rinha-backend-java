package com.marcelo_corrtes.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo_corrtes.api.dtos.TransactionDTO;

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

    /*
     * @PostMapping("/{id}/transacoes")
     * public String reateTransaction(@PathVariable Long id,
     * 
     * @RequestBody @Valid TransactionDTO entity) {
     * // response 200 ok
     * 
     * }
     */

    @GetMapping("/{id}/extrato")
    public String getExtractById(@PathVariable Long id) {
        return new String();
    }
}
