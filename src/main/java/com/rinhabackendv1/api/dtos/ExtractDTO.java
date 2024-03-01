package com.rinhabackendv1.api.dtos;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExtractDTO {

    @NotNull
    private BalanceDTO saldo;
    private LocalDateTime dataExtrato;
    private List<TransactionDTO> ultimasTransacoes;

}
