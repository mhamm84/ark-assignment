package com.ark.assignment.repository;

import com.ark.assignment.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomTransactionRepoImpl implements CustomTransactionRepo {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<TransactionEntity> findTransactionsForInvestor(Long investorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
       CriteriaQuery<TransactionEntity> query = cb.createQuery(TransactionEntity.class);
//        Root<TransactionEntity> root = query.from(TransactionEntity.class);
//        query.select(cb.construct(TransactionEntity.class, root.get(TransactionEntity), root.get(Author_.lastName)))
//                .where(cb.equal(root.get(Author_.firstName), firstName));

        return entityManager.createQuery(query).getResultList();
    }
}
