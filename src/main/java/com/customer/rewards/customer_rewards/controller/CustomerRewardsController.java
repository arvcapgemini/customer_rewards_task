package com.customer.rewards.customer_rewards.controller;

import com.customer.rewards.customer_rewards.entity.CustomerDetails;
import com.customer.rewards.customer_rewards.model.CustomerRewards;
import com.customer.rewards.customer_rewards.repository.CustomerRewardRepository;
import com.customer.rewards.customer_rewards.service.CustomerRewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-rewards")
public class CustomerRewardsController {
    @Autowired
    CustomerRewardsService customerRewardsService;

    @Autowired
    CustomerRewardRepository customerRepository;

    @GetMapping(value = "/{customerId}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerRewards> getRewardsByCustomerId(@PathVariable("customerId") Long customerId){
        CustomerDetails customerDetails = customerRepository.findCustomerById(customerId);
        if(customerDetails == null)
        {
            throw new RuntimeException("Invalid / Missing customer Id ");
        }
        CustomerRewards customerRewards = customerRewardsService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewards, HttpStatus.OK);
    }

}
