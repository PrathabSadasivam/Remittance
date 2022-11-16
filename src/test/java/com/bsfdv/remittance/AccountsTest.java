package com.bsfdv.remittance;

import com.bsfdv.remittance.contract.request.AccountRequest;
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

public class AccountsTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getAllAccounts() throws Exception {
        String uri = "/accounts";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<AccountResponse> accountlist = super.mapFromJson(content, List.class);
        assertTrue(accountlist.size() > 0);
    }

    @Test
    public void getAccountById() throws Exception {
        String uri = "/accounts/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        AccountResponse account = super.mapFromJson(content, AccountResponse.class);
        assertNotNull(account);
        assertEquals(account.getAccountId(), 1L);
    }

    @Test
    public void getAccountBalance() throws Exception {
        String uri = "/accounts/1/balance";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "100.0000");
    }

    /*@Test
    public void createAccount() throws Exception {
        String uri = "/accounts";
        AccountRequest accReq = new AccountRequest("Prathab",new BigDecimal(900.0000), "USD");
        String inputJson = super.mapToJson(accReq);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Account is created successfully");
    }*/

    @Test
    public void amountDepositTest() throws Exception {
        String uri = "/accounts/1/deposit/199";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        AccountResponse account = super.mapFromJson(content, AccountResponse.class);
        assertEquals(account.getBalance(), new BigDecimal(299.0000).setScale(4, RoundingMode.HALF_EVEN));
    }

    @Test
    public void amountWithdrawTest() throws Exception {
        String uri = "/accounts/2/withdraw/99";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        AccountResponse account = super.mapFromJson(content, AccountResponse.class);
        assertEquals(account.getBalance(), new BigDecimal(101.0000).setScale(4, RoundingMode.HALF_EVEN));
    }

    @Test
    public void deleteAccountTest() throws Exception {
        String uri = "/accounts/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Account deleted successfully");
    }
}
