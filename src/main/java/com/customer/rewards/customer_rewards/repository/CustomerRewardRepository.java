package com.customer.rewards.customer_rewards.repository;

import com.customer.rewards.customer_rewards.entity.CustomerDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRewardRepository extends CrudRepository<CustomerDetails,Long> {
    public CustomerDetails findCustomerById(Long custId);
}
