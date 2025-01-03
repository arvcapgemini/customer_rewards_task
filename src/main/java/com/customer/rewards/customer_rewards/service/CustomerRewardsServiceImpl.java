package com.customer.rewards.customer_rewards.service;

import com.customer.rewards.customer_rewards.CustomerConstants.CustomerConstants;
import com.customer.rewards.customer_rewards.entity.TransactionDetails;
import com.customer.rewards.customer_rewards.model.CustomerRewards;
import com.customer.rewards.customer_rewards.repository.CustomerTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerRewardsServiceImpl implements CustomerRewardsService {

    @Autowired
    CustomerTransactionRepository customerTransactionRepository;

    public CustomerRewards getRewardsByCustomerId(Long customerId) {

        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(CustomerConstants.daysInMonths);
        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*CustomerConstants.daysInMonths);
        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*CustomerConstants.daysInMonths);

        List<TransactionDetails> lastMonthTransactions = customerTransactionRepository.findAllCustomerByIdAndTransactionDateBetween(
                customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
        List<TransactionDetails> lastSecondMonthTransactions = customerTransactionRepository
                .findAllCustomerByIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
        List<TransactionDetails> lastThirdMonthTransactions = customerTransactionRepository
                .findAllCustomerByIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
        Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
        Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

        CustomerRewards customerRewards = new CustomerRewards();
        customerRewards.setCustId(customerId);
        customerRewards.setLastMonthPoints(lastMonthRewardPoints);
        customerRewards.setLastSecondMonthPoints(lastSecondMonthRewardPoints);
        customerRewards.setLastThirdMonthPoints(lastThirdMonthRewardPoints);
        customerRewards.setTotalCustomerRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

        return customerRewards;

    }

    private Long getRewardsPerMonth(List<TransactionDetails> transactions) {
        return transactions.stream().map(transaction -> calculateRewards(transaction))
                .collect(Collectors.summingLong(r -> r.longValue()));
    }

    private Long calculateRewards(TransactionDetails t) {
        if (t.getTransAmount() > CustomerConstants.firstRewardLimit && t.getTransAmount() <= CustomerConstants.secondRewardLimit) {
            return Math.round(t.getTransAmount() - CustomerConstants.firstRewardLimit);
        } else if (t.getTransAmount() > CustomerConstants.secondRewardLimit) {
            return Math.round(t.getTransAmount() - CustomerConstants.secondRewardLimit) * 2
                    + (CustomerConstants.secondRewardLimit - CustomerConstants.firstRewardLimit);
        } else
            return 0l;

    }

    public Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }

}
