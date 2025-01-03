package com.customer.rewards.customer_rewards.entity;

import jakarta.persistence.*;

@Entity
@Table(name="CUSTOMER_DETAILS")
public class CustomerDetails {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long id;
    @Column(name = "CUSTOMER_NAME")
    private String custName;
}

