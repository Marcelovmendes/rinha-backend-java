package com.marcelo_corrtes.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcelo_corrtes.api.models.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {
}
