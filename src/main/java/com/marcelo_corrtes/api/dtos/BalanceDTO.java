package com.marcelo_corrtes.api.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BalanceDTO {
    private int total;
    private LocalDateTime dataExtrato;
    private int limite;

}