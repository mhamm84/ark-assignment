package com.ark.assignment.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "fund")
public class FundEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    private String ticker;

    @Column(name = "created_date_time")
    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @Digits(integer = 15, fraction = 2)
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal balance;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "fund_id", nullable = false)
    private ClientEntity client;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "funds_investors",
            joinColumns = {
                    @JoinColumn(name = "fund_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "investor_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<InvestorEntity> investors;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "fund", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TransactionEntity> transactions;

    public void addInvestor(InvestorEntity toAdd) {
        investors.add(toAdd);
    }


}
