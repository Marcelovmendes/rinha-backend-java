package com.marcelo_corrtes.api.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BalanceDTO {

    @NotNull
    private int saldo;

    @NotNull
    private int limite;

}