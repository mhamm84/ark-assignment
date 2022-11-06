package com.ark.assignment.repository;

import com.ark.assignment.entity.InvestorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorRepository extends JpaRepository<InvestorEntity, Long> {
    
}
