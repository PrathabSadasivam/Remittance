package com.bsfdv.remittance.contract.response;



public class AccountHolderResponse {
    private String userName;
    private String emailAddress;

    public AccountHolderResponse() {
    }

    public AccountHolderResponse(String userName, String emailAddress) {
        this.userName = userName;
        this.emailAddress = emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "AccountHolderResponse{" +
                "userName='" + userName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
