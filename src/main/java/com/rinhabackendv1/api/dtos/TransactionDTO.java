package com.rinhabackendv1.api.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TransactionDTO {

    @NotNull
    @PositiveOrZero
    private Integer valor;

    @NotEmpty
    @Pattern(regexp = "[cd]")
    private String tipo;

    @NotBlank
    @Size(min = 1, max = 10)
    private String descricao;

    private LocalDateTime realizadaEm;

}
