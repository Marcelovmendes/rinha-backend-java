package com.marcelo_corrtes.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo_corrtes.api.models.ClientModel;

@RestController()
@RequestMapping("/clientes")
public class ClientController {

    @GetMapping
    public String GetListClient() {

        return "Lista de Clientes";

    }
}