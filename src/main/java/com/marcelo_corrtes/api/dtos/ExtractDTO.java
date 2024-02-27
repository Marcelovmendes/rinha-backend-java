package com.marcelo_corrtes.api.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ExtractDTO {
    private BalanceDTO saldo;
    private List<TransactionDTO> ultimasTransacoes;

}
