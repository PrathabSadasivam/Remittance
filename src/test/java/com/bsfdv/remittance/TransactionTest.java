package com.bsfdv.remittance;

import com.bsfdv.remittance.contract.request.UserTransaction;
import com.bsfdv.remittance.contract.response.AccountResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


    @Test
    public void transactionTest() throws Exception {
        String fromAccountUri = "/accounts/1";
        String toAccountUri = "/accounts/2";
        String txnUri = "/transaction";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(fromAccountUri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        AccountResponse fromAccount = super.mapFromJson(content, AccountResponse.class);

        mvcResult = mvc.perform(MockMvcRequestBuilders.get(toAccountUri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        content = mvcResult.getResponse().getContentAsString();
        AccountResponse toAccount = super.mapFromJson(content, AccountResponse.class);

        UserTransaction txn = new UserTransaction("USD", new BigDecimal(5),
                fromAccount.getAccountId(), toAccount.getAccountId());

        String inputJson = super.mapToJson(txn);
        MvcResult mvcPostResult = mvc.perform(MockMvcRequestBuilders.post(txnUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        content = mvcPostResult.getResponse().getContentAsString();
        assertEquals(content, "Funds transferred successfully");

        mvcResult = mvc.perform(MockMvcRequestBuilders.get(fromAccountUri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        content = mvcResult.getResponse().getContentAsString();
        AccountResponse fromAccountAferTxn = super.mapFromJson(content, AccountResponse.class);

        mvcResult = mvc.perform(MockMvcRequestBuilders.get(toAccountUri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        content = mvcResult.getResponse().getContentAsString();
        AccountResponse toAccountAferTxn = super.mapFromJson(content, AccountResponse.class);
        assertTrue(fromAccount.getBalance().compareTo(fromAccountAferTxn.getBalance()) > 0 );
        assertTrue(toAccount.getBalance().compareTo(toAccountAferTxn.getBalance()) < 0 );
    }


}
