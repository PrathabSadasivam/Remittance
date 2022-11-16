package com.bsfdv.remittance;

import com.bsfdv.remittance.contract.request.AccountHolderRequest;
import com.bsfdv.remittance.contract.response.AccountHolderResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.*;

public class AccountHolderTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getAllAccountHolders() throws Exception {
        String uri = "/account-holders/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<AccountHolderResponse> accountHolders = super.mapFromJson(content, List.class);
        assertTrue(accountHolders.size() > 0);
    }

    @Test
    public void getAccountHolderByName() throws Exception {
        String uri = "/account-holders/Prathab";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        AccountHolderResponse accountHolder = super.mapFromJson(content, AccountHolderResponse.class);
        assertEquals(accountHolder.getEmailAddress(), "Prathab@gmail.com");
    }

    @Test
    public void deleteAccountHolderTest() throws Exception {
        String uri = "/account-holders/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Account holder deleted successfully");
    }

    @Test
    public void updateAccountHolderTest() throws Exception {
        String uri = "/account-holders/4";
        AccountHolderRequest accReq = new AccountHolderRequest("Prathab Sadasivam","prathab.sadasivam@gmail.com");
        String inputJson = super.mapToJson(accReq);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        AccountHolderResponse accountHolder = super.mapFromJson(content, AccountHolderResponse.class);
        assertEquals(accountHolder.getEmailAddress(), "prathab.sadasivam@gmail.com");
    }

    @Test
    public void createAccountHolderTest() throws Exception {
        String uri = "/account-holders/create";
        AccountHolderRequest accReq = new AccountHolderRequest("Prathab Sadasivam","prathab.sadasivam@gmail.com");
        String inputJson = super.mapToJson(accReq);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Account is created successfully");
    }
}
