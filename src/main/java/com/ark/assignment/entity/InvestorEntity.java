package com.ark.assignment.entity;

import lombok.*;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "investor")
@Data
public class InvestorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_date_time")
    @CreationTimestamp
    private LocalDateTime createdDateTime;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {
            CascadeType.ALL
    }, fetch = FetchType.LAZY,
            mappedBy = "investors")
    private List<FundEntity> funds;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "investor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TransactionEntity> transactions;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

}
