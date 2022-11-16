package com.bsfdv.remittance.service;

import com.bsfdv.remittance.contract.request.UserTransaction;
import com.bsfdv.remittance.exception.CustomException;
import com.bsfdv.remittance.contract.request.AccountRequest;
import com.bsfdv.remittance.contract.response.AccountResponse;
import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<AccountResponse> findAllAccounts() throws CustomException;
    AccountResponse findAccount(long accountId) throws CustomException;
    void deleteAccount(long accountId) throws CustomException;
    AccountResponse createAccount(AccountRequest accountRequest) throws CustomException;
    AccountResponse accountDeposit(long accountId, BigDecimal amount) throws CustomException;
    AccountResponse withdrawAmount(long accountId, BigDecimal amount) throws CustomException;
    void transferFund(UserTransaction transaction) throws CustomException;
}
