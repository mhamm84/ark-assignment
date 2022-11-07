package com.ark.assignment.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transaction_type")
public class TransactionTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
