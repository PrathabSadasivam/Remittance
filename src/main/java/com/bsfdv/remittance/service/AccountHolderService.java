package com.bsfdv.remittance.service;

import com.bsfdv.remittance.contract.request.AccountHolderRequest;
import com.bsfdv.remittance.contract.response.AccountHolderResponse;
import com.bsfdv.remittance.exception.CustomException;

import java.util.List;

public interface AccountHolderService {
    //findUserNameByName(userName)
    List<AccountHolderResponse> getAllAccountHolders()  throws CustomException;
    AccountHolderResponse findByUserName(String userName) throws CustomException;
    AccountHolderResponse createAccountHolder(AccountHolderRequest accountHolder) throws CustomException;
    AccountHolderResponse updateAccountHolder(long userId, AccountHolderRequest accountHolder) throws CustomException;
    void deleteAccountHolder(long userId) throws CustomException;
}
