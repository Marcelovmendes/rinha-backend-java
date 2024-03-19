package com.rinhabackendv1.api.dtos;

import com.rinhabackendv1.api.models.ClientModel;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BalanceDTO {

    @NotNull
    private int saldo;

    @NotNull
    private int limite;
  
    public BalanceDTO(ClientModel client) {
        this.saldo = client.getSaldo();
        this.limite = client.getLimite();
    }
}