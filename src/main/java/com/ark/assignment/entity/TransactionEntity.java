package com.ark.assignment.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "created_date_time")
    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_type_id")
    private TransactionTypeEntity type;

    @Digits(integer = 15, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal amount;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "investor_id", nullable = false)
    private InvestorEntity investor;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "fund_id", nullable = false)
    private FundEntity fund;

}
