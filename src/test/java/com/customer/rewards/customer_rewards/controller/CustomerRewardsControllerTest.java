package com.customer.rewards.customer_rewards.controller;

import com.customer.rewards.customer_rewards.entity.CustomerDetails;
import com.customer.rewards.customer_rewards.model.CustomerRewards;
import com.customer.rewards.customer_rewards.repository.CustomerRewardRepository;
import com.customer.rewards.customer_rewards.service.CustomerRewardsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRewardsControllerTest {

    @InjectMocks
    CustomerRewardsController customerRewardsController;

    @Mock
    CustomerRewardRepository customerRewardRepository;

    @Mock
    CustomerRewardsService customerRewardsService;

    @Test
    public void testGetRewardsByCustomerId()
    {
        CustomerDetails customerDetails=new CustomerDetails();
        customerDetails.setCustName("ut_test");
        customerDetails.setId(12345L);

        CustomerRewards customerRewards=new CustomerRewards();
        customerRewards.setTotalCustomerRewards(123456789L);
        customerRewards.setCustId(12345L);
        customerRewards.setLastMonthPoints(123456L);
        customerRewards.setLastSecondMonthPoints(1234567L);
        customerRewards.setLastThirdMonthPoints(12345687);
        Mockito.when(customerRewardRepository.findCustomerById(Mockito.any())).thenReturn(customerDetails);
        Mockito.when(customerRewardsService.getRewardsByCustomerId(Mockito.any())).thenReturn(customerRewards);

        ResponseEntity<CustomerRewards> responseEntity= customerRewardsController.getRewardsByCustomerId(12345L);

        assertNotNull(responseEntity);
    }

    @Test
    public void testGetRewardsByCustomerId_CustIdNull()
    {
        Mockito.when(customerRewardRepository.findCustomerById(Mockito.any())).thenReturn(null);
        try {
            ResponseEntity<CustomerRewards> responseEntity = customerRewardsController.getRewardsByCustomerId(12345L);
        }
        catch (RuntimeException exception)
        {
            assertEquals(exception.getMessage(),"Invalid / Missing customer Id ");
        }
    }
}