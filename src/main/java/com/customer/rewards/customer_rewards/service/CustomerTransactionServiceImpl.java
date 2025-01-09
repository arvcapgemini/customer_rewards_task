package com.customer.rewards.customer_rewards.service;

import com.customer.rewards.customer_rewards.entity.CustomerDetails;
import com.customer.rewards.customer_rewards.entity.TransactionDetails;
import com.customer.rewards.customer_rewards.repository.CustomerTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerTransactionServiceImpl implements CustomerTransactionService{

    @Autowired
    CustomerTransactionRepository customerTransactionRepository;
    /**
     * @param transactionDetails
     * @return Transactions details
     */
    @Override
    public TransactionDetails saveTransactions(TransactionDetails transactionDetails) {
        return customerTransactionRepository.save(transactionDetails);
    }
}
