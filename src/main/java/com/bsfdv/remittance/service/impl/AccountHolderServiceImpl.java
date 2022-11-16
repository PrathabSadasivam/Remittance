package com.bsfdv.remittance.service.impl;

import com.bsfdv.remittance.contract.request.AccountHolderRequest;
import com.bsfdv.remittance.contract.response.AccountHolderResponse;
import com.bsfdv.remittance.dao.model.AccountHolder;
import com.bsfdv.remittance.dao.repository.UserRepository;
import com.bsfdv.remittance.exception.CustomException;
import com.bsfdv.remittance.service.AccountHolderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountHolderServiceImpl implements AccountHolderService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AccountHolderResponse> getAllAccountHolders() throws CustomException {
        List<AccountHolder> accountHolders = userRepository.findAll();
        List<AccountHolderResponse> accountHoldersResp = modelMapper.map(accountHolders, List.class);
        return accountHoldersResp;
    }

    @Override
    public AccountHolderResponse findByUserName(String userName) throws CustomException {
        AccountHolder accountHolder = userRepository.findUserNameByName(userName);
        return modelMapper.map(accountHolder, AccountHolderResponse.class);
    }

    @Override
    public AccountHolderResponse createAccountHolder(AccountHolderRequest accountHolderReq) throws CustomException {
        AccountHolder accountHolder = modelMapper.map(accountHolderReq, AccountHolder.class);
        long createdUserId = userRepository.save(accountHolder).getUserId();
        AccountHolder createdAccountHolder = userRepository.findById(createdUserId).get();
        return modelMapper.map(createdAccountHolder, AccountHolderResponse.class);
    }

    @Override
    public AccountHolderResponse updateAccountHolder(long userId, AccountHolderRequest accountHolderReq) throws CustomException {
        AccountHolder accountHolder = userRepository.findById(userId).get();
        accountHolder.setUserName(accountHolderReq.getUserName());
        accountHolder.setEmailAddress(accountHolderReq.getEmailAddress());
        userRepository.save(accountHolder);
        return modelMapper.map(accountHolder, AccountHolderResponse.class);
    }

    @Override
    public void deleteAccountHolder(long userId) throws CustomException {
        userRepository.findById(userId).ifPresent(x -> userRepository.deleteById(x.getUserId()));
    }
}
