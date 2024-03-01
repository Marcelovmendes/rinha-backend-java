package com.rinhabackendv1.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rinhabackendv1.api.models.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {
}
