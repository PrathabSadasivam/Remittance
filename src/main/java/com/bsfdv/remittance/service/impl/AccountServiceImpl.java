package com.bsfdv.remittance.service.impl;

import com.bsfdv.remittance.contract.request.UserTransaction;
import com.bsfdv.remittance.exception.CustomException;
import com.bsfdv.remittance.dao.model.Account;
import com.bsfdv.remittance.dao.repository.AccountsRepository;
import com.bsfdv.remittance.contract.request.AccountRequest;
import com.bsfdv.remittance.contract.response.AccountResponse;
import com.bsfdv.remittance.service.AccountService;
import com.bsfdv.remittance.utils.RemitUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public List<AccountResponse> findAllAccounts() throws CustomException {
        List<Account> accounts = accountsRepository.findAll();
        List<AccountResponse> accountsResp = modelMapper.map(accounts, List.class);
        return accountsResp;
    }

    @Override
    public AccountResponse findAccount(long accountId) throws CustomException {
        Account account = accountsRepository.findById(accountId).get();
        return modelMapper.map(account, AccountResponse.class);
    }

    @Override
    public void deleteAccount(long accountId) throws CustomException {
        accountsRepository.deleteById(accountId);
    }

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) throws CustomException {
        Account account = modelMapper.map(accountRequest, Account.class);
        long createdAccountId = accountsRepository.save(account).getAccountId();
        Account createdAccount = accountsRepository.findById(createdAccountId).get();
        return modelMapper.map(createdAccount, AccountResponse.class);
    }

    @Override
    public AccountResponse accountDeposit(long accountId, BigDecimal amount) throws CustomException {
        Account account = accountsRepository.updateAccountBalace(accountId);
        BigDecimal balance = account.getBalance().add(amount);
        if (balance.compareTo(RemitUtils.zeroAmount) < 0) {
            throw new CustomException("Not sufficient Fund for account: " + accountId);
        }
        account.setBalance(balance);
        accountsRepository.save(account);
        return modelMapper.map(account, AccountResponse.class);
    }

    @Override
    public AccountResponse withdrawAmount(long accountId, BigDecimal amount) throws CustomException {
        Account account = accountsRepository.updateAccountBalace(accountId);
        BigDecimal delta = amount.negate();
        BigDecimal balance = account.getBalance().add(delta);
        if (balance.compareTo(RemitUtils.zeroAmount) < 0) {
            throw new CustomException("Not sufficient Fund for account: " + accountId);
        }
        account.setBalance(balance);
        accountsRepository.save(account);
        return modelMapper.map(account, AccountResponse.class);
    }

    @Override
    public void transferFund(UserTransaction transaction) throws CustomException {
        String currency = transaction.getCurrencyCode();
        if (RemitUtils.INSTANCE.validateCcyCode(currency)) {
            Account fromAccount = accountsRepository.updateAccountBalace(transaction.getFromAccountId());
            Account toAccount = accountsRepository.updateAccountBalace(transaction.getToAccountId());

            BigDecimal delta = transaction.getAmount().negate();
            BigDecimal balance = fromAccount.getBalance().add(delta);
            if (balance.compareTo(RemitUtils.zeroAmount) < 0) {
                throw new CustomException("Not sufficient Fund for account: " + fromAccount.getAccountId());
            }

            fromAccount.setBalance(balance);
            accountsRepository.save(fromAccount);

            BigDecimal depositAmount = toAccount.getBalance().add(transaction.getAmount());
            toAccount.setBalance(depositAmount);
            accountsRepository.save(toAccount);
        }
    }
}
