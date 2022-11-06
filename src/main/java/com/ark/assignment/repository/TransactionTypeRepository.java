package com.ark.assignment.repository;

import com.ark.assignment.entity.TransactionTypeEntity;
import com.ark.assignment.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeEntity, Long> {

    TransactionTypeEntity findByName(String name);
}
