package com.ark.assignment.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
public class TransactionTypeSummaryEntity {

    private String type;
    private BigDecimal total;

    public TransactionTypeSummaryEntity(String type, BigDecimal total) {
        this.type = type;
        this.total = total;
    }
}
