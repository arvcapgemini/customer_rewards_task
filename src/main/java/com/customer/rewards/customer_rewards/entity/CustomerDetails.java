package com.customer.rewards.customer_rewards.entity;

import jakarta.persistence.*;

@Entity
@Table(name="CUSTOMER_DETAILS")
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long custId;
    @Column(name = "CUSTOMER_NAME")
    private String custName;
}

