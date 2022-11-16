package com.bsfdv.remittance.controller;


import com.bsfdv.remittance.contract.request.UserTransaction;
import com.bsfdv.remittance.exception.CustomException;
import com.bsfdv.remittance.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    AccountService accountService;

    /**
     * Transfer fund between two accounts.
     *
     * @param transaction
     * @return
     * @throws CustomException
     */

    @PostMapping("/transaction")
    public String transferFund(@RequestBody UserTransaction transaction) throws CustomException {
        accountService.transferFund(transaction);
        return "Funds transferred successfully";
    }

}

