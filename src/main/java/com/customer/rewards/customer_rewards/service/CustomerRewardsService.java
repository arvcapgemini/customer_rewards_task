package com.customer.rewards.customer_rewards.service;

import com.customer.rewards.customer_rewards.entity.CustomerDetails;
import com.customer.rewards.customer_rewards.model.CustomerRewards;

public interface CustomerRewardsService {

    public CustomerRewards getRewardsByCustomerId(Long custId);
    public CustomerDetails saveCustomers(CustomerDetails customerDetails);
}
