package com.rinhabackendv1.api.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transacoes")
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int valor;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false, length = 250)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClientModel cliente;

    @Column(name = "realizado_em")
    @CreationTimestamp
    private LocalDateTime realizadoEm;

}
