package com.customer.rewards.customer_rewards.service;

import com.customer.rewards.customer_rewards.entity.TransactionDetails;
import com.customer.rewards.customer_rewards.model.CustomerRewards;
import com.customer.rewards.customer_rewards.repository.CustomerTransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRewardsServiceImplTest {

    @InjectMocks
    CustomerRewardsServiceImpl customerRewardsService;

    @Mock
    CustomerTransactionRepository customerTransactionRepository;

    @Test
    public void testGetRewardsByCustomerId()
    {

        List<TransactionDetails> transactionDetailsList=new ArrayList<>();
        Timestamp instant= Timestamp.from(Instant.now());
        TransactionDetails transactionDetails=new TransactionDetails();
        transactionDetails.setCustomerId(12345L);
        transactionDetails.setTransactionAmount(12345);
        transactionDetails.setTransactionDate(instant);

        transactionDetailsList.add(transactionDetails);

        Mockito.when(customerTransactionRepository.findAllByCustomerIdAndTransactionDateBetween(Mockito.any()
        ,Mockito.any(),Mockito.any())).thenReturn(transactionDetailsList);


        CustomerRewards customerRewards=customerRewardsService.getRewardsByCustomerId(1001L);

        assertNotNull(customerRewards);
        assertEquals(customerRewards.getCustId(),1001);
        assertEquals(customerRewards.getLastMonthPoints(),24540);
        assertEquals(customerRewards.getLastSecondMonthPoints(),24540);
        assertEquals(customerRewards.getLastThirdMonthPoints(),24540);
        assertEquals(customerRewards.getTotalCustomerRewards(),73620);

    }

}