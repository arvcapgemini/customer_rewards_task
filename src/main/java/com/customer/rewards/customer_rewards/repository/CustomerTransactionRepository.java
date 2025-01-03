package com.customer.rewards.customer_rewards.repository;


import com.customer.rewards.customer_rewards.entity.TransactionDetails;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public interface CustomerTransactionRepository  extends CrudRepository<TransactionDetails,Long> {
    public List<TransactionDetails> findAllCustomerById(Long custId);

    public List<TransactionDetails> findAllCustomerByIdAndTransactionDateBetween(Long customerId, Timestamp from, Timestamp to);
}
