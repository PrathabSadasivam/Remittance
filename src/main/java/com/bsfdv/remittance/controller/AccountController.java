package com.bsfdv.remittance.controller;

import com.bsfdv.remittance.exception.CustomException;

import com.bsfdv.remittance.contract.request.AccountRequest;
import com.bsfdv.remittance.contract.response.AccountResponse;
import com.bsfdv.remittance.service.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Account Service
 */
@RestController
public class AccountController {

    private static Logger log = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;

    /**
     * Find all accounts
     *
     * @return
     * @throws
     */
    @GetMapping("/accounts")
    public List<AccountResponse> getAllAccounts() throws CustomException {
        return accountService.findAllAccounts();
    }

    /**
     * Find by account id
     *
     * @param accountId
     * @return
     * @throws CustomException
     */
    @GetMapping("/accounts/{accountId}")
    public AccountResponse getAccount(@PathVariable("accountId") long accountId) throws CustomException {
        return accountService.findAccount(accountId);
    }

    /**
     * Find balance by account Id
     *
     * @param accountId
     * @return
     * @throws CustomException
     */
    @GetMapping("/accounts/{accountId}/balance")
    public BigDecimal getBalance(@PathVariable("accountId") long accountId) throws CustomException {
        AccountResponse accountResp = accountService.findAccount(accountId);
        return accountResp.getBalance();
    }

    /**
     * Create Account
     *
     * @param accountReq
     * @return
     * @throws CustomException
     */
    @PostMapping("/accounts")
    public AccountResponse createAccount(@RequestBody AccountRequest accountReq) throws CustomException {
        AccountResponse accountResp = accountService.createAccount(accountReq);
        return accountResp;
    }

    /**
     * Deposit amount by account Id
     *
     * @param accountId
     * @param amount
     * @return
     * @throws CustomException
     */
    @PutMapping("/accounts/{accountId}/deposit/{amount}")
    public AccountResponse deposit(@PathVariable("accountId") long accountId, @PathVariable("amount") BigDecimal amount) throws CustomException {
        AccountResponse accountResp = accountService.accountDeposit(accountId, amount);
        return accountResp;
    }

    /**
     * Withdraw amount by account Id
     *
     * @param accountId
     * @param amount
     * @return
     * @throws CustomException
     */
    @PutMapping("/accounts/{accountId}/withdraw/{amount}")
    public AccountResponse withdraw(@PathVariable("accountId") long accountId, @PathVariable("amount") BigDecimal amount) throws CustomException {
        AccountResponse accountResp = accountService.withdrawAmount(accountId, amount);
        return accountResp;
    }


    /**
     * Delete amount by account Id
     * @param accountId
     * @return
     * @throws CustomException
     */
    @DeleteMapping("/accounts/{accountId}")
    public String deleteAccount(@PathVariable("accountId") long accountId) throws CustomException {
        accountService.deleteAccount(accountId);
        return "Account deleted successfully";
    }

}
