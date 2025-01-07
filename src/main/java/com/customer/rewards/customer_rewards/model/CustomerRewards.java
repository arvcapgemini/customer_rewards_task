package com.customer.rewards.customer_rewards.model;

public class CustomerRewards {

    private long custId;
    private long lastMonthPoints;
    private long lastSecondMonthPoints;
    private long lastThirdMonthPoints;
    private long totalCustomerRewards;

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public long getLastMonthPoints() {
        return lastMonthPoints;
    }

    public void setLastMonthPoints(long lastMonthPoints) {
        this.lastMonthPoints = lastMonthPoints;
    }

    public long getLastSecondMonthPoints() {
        return lastSecondMonthPoints;
    }

    public void setLastSecondMonthPoints(long lastSecondMonthPoints) {
        this.lastSecondMonthPoints = lastSecondMonthPoints;
    }

    public long getLastThirdMonthPoints() {
        return lastThirdMonthPoints;
    }

    public void setLastThirdMonthPoints(long lastThirdMonthPoints) {
        this.lastThirdMonthPoints = lastThirdMonthPoints;
    }

    public long getTotalCustomerRewards() {
        return totalCustomerRewards;
    }

    public void setTotalCustomerRewards(long totalCustomerRewards) {
        this.totalCustomerRewards = totalCustomerRewards;
    }
}
