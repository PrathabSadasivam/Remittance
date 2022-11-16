package com.bsfdv.remittance.contract.request;

import java.math.BigDecimal;

public class AccountRequest {
    private String userName;
    private BigDecimal balance;
    private String currencyCode;

    public AccountRequest() {
    }

    public AccountRequest(String userName, BigDecimal balance, String currencyCode) {
        this.userName = userName;
        this.balance = balance;
        this.currencyCode = currencyCode;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "AccountResponse{" +
                ", userName='" + userName + '\'' +
                ", balance=" + balance +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
