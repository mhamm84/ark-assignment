package com.ark.assignment.repository;

import com.ark.assignment.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    
}
