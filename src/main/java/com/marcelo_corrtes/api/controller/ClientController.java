package com.marcelo_corrtes.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/clientes")
public class ClientController {

    @GetMapping
    public String GetListClient() {

    }
}