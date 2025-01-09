package com.customer.rewards.customer_rewards.controller;

import com.customer.rewards.customer_rewards.entity.CustomerDetails;
import com.customer.rewards.customer_rewards.entity.TransactionDetails;
import com.customer.rewards.customer_rewards.model.CustomerRewards;
import com.customer.rewards.customer_rewards.repository.CustomerRewardRepository;
import com.customer.rewards.customer_rewards.repository.CustomerTransactionRepository;
import com.customer.rewards.customer_rewards.service.CustomerRewardsService;
import com.customer.rewards.customer_rewards.service.CustomerTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller
 * @author : Aravindh Andhonisamy
 * **The rest API to get customer rewards based on customer Id**
 */
@RestController
@RequestMapping("/customer-rewards")
public class CustomerRewardsController {

    @Autowired
    CustomerRewardsService customerRewardsService;

    @Autowired
    CustomerRewardRepository customerRepository;

    @Autowired
    CustomerTransactionRepository customerTransactionRepository;

    @Autowired
    CustomerTransactionService customerTransactionService;

    /**
     * @param customerId
     * @return Customer Rewards
     * @apiNote **A retailer offers a rewards program to its customers, awarding points based on each recorded purchase**
     */
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

    /**
     * @param customer
     * @return Customer Rewards
     * @apiNote **A retailer offers a rewards program to its customers, awarding points based on each recorded purchase**
     * @implNote To save the customers to give the rewards
     */
    @PostMapping(value = "/saveCustomers",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDetails> saveCustomer(@RequestBody CustomerDetails customer)
    {
        CustomerDetails customerDetails=customerRewardsService.saveCustomers(customer);
        return new ResponseEntity<>(customerDetails,HttpStatus.OK);
    }
    /**
     * @param transactionDetails
     * @return Customer Rewards
     * @apiNote **A retailer offers a rewards program to its customers, awarding points based on each recorded purchase**
     * @implNote To save the transactions to track the points
     */
    @PostMapping(value = "/saveTransactions",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDetails> saveTransaction(@RequestBody TransactionDetails transactionDetails)
    {
        TransactionDetails transactions=customerTransactionService.saveTransactions(transactionDetails);
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }
}
