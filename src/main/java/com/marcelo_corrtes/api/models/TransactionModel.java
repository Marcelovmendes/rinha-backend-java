package com.marcelo_corrtes.api.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
