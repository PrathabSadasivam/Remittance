package com.bsfdv.remittance.controller;


import com.bsfdv.remittance.contract.request.AccountHolderRequest;
import com.bsfdv.remittance.contract.response.AccountHolderResponse;
import com.bsfdv.remittance.exception.CustomException;
import com.bsfdv.remittance.service.AccountHolderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-holders")
public class AccountHolderController {
	@Autowired
	private AccountHolderService accountHolderService;
	private static Logger log = LoggerFactory.getLogger(AccountHolderController.class);

	/**
	 * Find by userName
	 * @param userName
	 * @return
	 * @throws CustomException
	 */

    @GetMapping("/{userName}")
    public AccountHolderResponse getUserByName(@PathVariable("userName") String userName) throws CustomException {
        return accountHolderService.findByUserName(userName);
    }
    

/**
	 * Find by all
	 * @return
	 * @throws CustomException
	 */

	@GetMapping("/")
    public List<AccountHolderResponse> getAllUsers() throws CustomException {
        return accountHolderService.getAllAccountHolders();
    }
    

/**
     * Create User
     * @param user
     * @return
     * @throws CustomException
     */

    @PostMapping("/create")
    public AccountHolderResponse createUser(@RequestBody AccountHolderRequest user) throws CustomException {
        return accountHolderService.createAccountHolder(user);
    }

/**
     * Find by User Id
     * @param userId
     * @param user
     * @return
     * @throws CustomException
     */

    @PutMapping("/{userId}")
    public AccountHolderResponse updateUser(@PathVariable("userId") long userId, @RequestBody AccountHolderRequest user) throws CustomException {
        return accountHolderService.updateAccountHolder(userId, user);
    }


/**
     * Delete by User Id
     * @param userId
     * @return
     * @throws CustomException
     */

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") long userId) throws CustomException {
        accountHolderService.deleteAccountHolder(userId);
        return "Account holder deleted successfully";
    }
}

