package com.customer.rewards.customer_rewards.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="TRANS_DETAILS")
public class TransactionDetails {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long transId;

    @Column(name="CUSTOMER_ID")
    private Long custId;

    @Column(name = "TRANSACTION_DATE")
    private Timestamp transDate;

    @Column(name = "AMOUNT")
    private double transAmount;

    public Long getTransId() {
        return transId;
    }

    public void setTransId(Long transId) {
        this.transId = transId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Timestamp getTransDate() {
        return transDate;
    }

    public void setTransDate(Timestamp transDate) {
        this.transDate = transDate;
    }

    public double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(double transAmount) {
        this.transAmount = transAmount;
    }


}
