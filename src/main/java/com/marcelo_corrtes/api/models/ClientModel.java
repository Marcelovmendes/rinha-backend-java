package com.marcelo_corrtes.api.models;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "clients")
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private int saldo;

    @Column(nullable = false)
    private int limite;

}
