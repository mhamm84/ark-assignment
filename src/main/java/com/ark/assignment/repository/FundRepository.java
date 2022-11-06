package com.ark.assignment.repository;

import com.ark.assignment.entity.FundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<FundEntity, Long> {
    
}
