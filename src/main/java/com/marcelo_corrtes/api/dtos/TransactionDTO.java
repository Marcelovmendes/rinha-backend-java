package com.marcelo_corrtes.api.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class TransactionDTO {

    @NotBlank
    @PositiveOrZero
    private BigDecimal valor;

    @NotEmpty
    @Pattern(regexp = "[cd]")
    private String tipo;

    @NotBlank
    private String descricao;

}
