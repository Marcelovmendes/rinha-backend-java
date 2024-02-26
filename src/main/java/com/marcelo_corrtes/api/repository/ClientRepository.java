package com.marcelo_corrtes.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo_corrtes.api.models.ClientModel;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {
}
