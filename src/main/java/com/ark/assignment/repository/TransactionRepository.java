package com.ark.assignment.repository;

import com.ark.assignment.entity.TransactionEntity;
import com.ark.assignment.entity.TransactionTypeSummaryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT DISTINCT t FROM TransactionEntity t JOIN FETCH t.investor inv JOIN FETCH t.fund fund WHERE inv.id = :investorId AND fund.id = :fundId")
    List<TransactionEntity> findTransactionsForInvestor(@Param("fundId") Long fundId, @Param("investorId") Long investorId);

    @Query("SELECT DISTINCT t FROM TransactionEntity t JOIN FETCH t.investor inv JOIN FETCH t.fund fund WHERE inv.id = :investorId AND fund.id = :fundId AND t.createdDateTime >= :from")
    List<TransactionEntity> findTransactionsForInvestorFrom(@Param("fundId") Long fundId, @Param("investorId") Long investorId, @Param("from") LocalDateTime from);

    @Query("SELECT DISTINCT t FROM TransactionEntity t JOIN FETCH t.investor inv JOIN FETCH t.fund fund WHERE inv.id = :investorId AND fund.id = :fundId AND t.createdDateTime BETWEEN :start AND :end")
    List<TransactionEntity> findTransactionsForInvestorBetween(@Param("fundId") Long fundId, @Param("investorId") Long investorId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT DISTINCT t FROM TransactionEntity t JOIN FETCH t.investor inv JOIN FETCH t.fund fund WHERE inv.id = :investorId AND fund.id = :fundId AND t.createdDateTime >= :from AND t.type.name = :typeName")
    List<TransactionEntity> findTransactionsForInvestorFromWithType(@Param("fundId") Long fundId, @Param("investorId") Long investorId, @Param("from") LocalDateTime from, @Param("typeName") String typeName);

    @Query("SELECT DISTINCT t FROM TransactionEntity t JOIN FETCH t.investor inv JOIN FETCH t.fund fund WHERE inv.id = :investorId AND fund.id = :fundId AND t.createdDateTime BETWEEN :start AND :end AND t.type.name = :typeName")
    List<TransactionEntity> findTransactionsForInvestorBetweenWithType(@Param("fundId") Long fundId, @Param("investorId") Long investorId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("typeName") String typeName);

    @Query("SELECT DISTINCT t FROM TransactionEntity t JOIN FETCH t.investor inv JOIN FETCH t.fund fund WHERE fund.id = :fundId AND t.createdDateTime >= :from")
    List<TransactionEntity> findTransactionsForFundFrom(@Param("fundId") Long fundId, @Param("from") LocalDateTime from);

    @Query("SELECT DISTINCT t FROM TransactionEntity t JOIN FETCH t.investor inv JOIN FETCH t.fund fund WHERE fund.id = :fundId AND t.createdDateTime BETWEEN :start AND :end")
    List<TransactionEntity> findTransactionsForFundBetween(@Param("fundId") Long fundId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


    @Query("select new com.ark.assignment.entity.TransactionTypeSummaryEntity(t.type.name as type, sum(t.amount) as total) "
            + "from TransactionEntity t "
            + "JOIN t.investor inv JOIN t.fund fund "
            + "WHERE inv.id = :investorId AND fund.id = :fundId "
            + "group by t.type.id")
    List<TransactionTypeSummaryEntity> findTransactionsForInvestorSummary(
            @Param("fundId") Long fundId, @Param("investorId") Long investorId);

    @Query("select new com.ark.assignment.entity.TransactionTypeSummaryEntity(t.type.name as type, sum(t.amount) as total) "
            + "from TransactionEntity t "
            + "JOIN t.investor inv JOIN t.fund fund "
            + "WHERE inv.id = :investorId AND fund.id = :fundId AND t.createdDateTime >= :from "
            + "group by t.type.id")
    List<TransactionTypeSummaryEntity> findTransactionsForInvestorFromSummary(
            @Param("fundId") Long fundId, @Param("investorId") Long investorId, @Param("from") LocalDateTime from);

    @Query("select new com.ark.assignment.entity.TransactionTypeSummaryEntity(t.type.name as type, sum(t.amount) as total) "
            + "from TransactionEntity t "
            + "JOIN t.investor inv JOIN t.fund fund "
            + "WHERE inv.id = :investorId AND fund.id = :fundId AND t.createdDateTime BETWEEN :start AND :end "
            + "group by t.type.id")
    List<TransactionTypeSummaryEntity> findTransactionsForInvestorBetweenSummary(
            @Param("fundId") Long fundId, @Param("investorId") Long investorId, @Param("start") LocalDateTime from, @Param("end") LocalDateTime end);




}
