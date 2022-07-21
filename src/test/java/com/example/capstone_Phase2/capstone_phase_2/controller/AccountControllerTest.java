package com.example.capstone_Phase2.capstone_phase_2.controller;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Account;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Customer;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Transfer;
import com.example.capstone_Phase2.capstone_phase_2.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(SpringExtension.class) //@RunsWith(SpringRunner.class)
@WebMvcTest(AccountController.class)@TestMethodOrder(OrderAnnotation.class)

class AccountControllerTest {
  @Autowired
  private MockMvc mockmvc;
  @MockBean
  private AccountService service;
  
  @Test
  @Order(2)
  void getAccounts() throws Exception {
	  RequestBuilder request;
	  List<Account> asList = Arrays.asList(
			  new Account(1,"Savings",100000),
			  new Account(2,"Current",80000)
			  );
	  System.out.println("Response :"+asList.toString());
	  when(service.GetAllAccounts()).thenReturn(asList);
	  request=MockMvcRequestBuilders
			  .get("/api/accounts")
			  .accept(MediaType.APPLICATION_JSON);
	  String expectedResult="[{account_Number:1,accountType:Savings,balance:100000},{account_Number:2,accountType:Current,balance:80000}]";
	  MvcResult result =mockmvc.perform(request)
			  .andExpect(status().isOk())
			  .andExpect(content().json(expectedResult))
			  .andReturn();
	  MockHttpServletResponse response = result.getResponse();
	  assertEquals(HttpStatus.OK.value(), response.getStatus());
  }
  @Test
  @Order(1) 
  void createAccount() throws Exception {
	  RequestBuilder request;
	  ObjectMapper objectMapper=new ObjectMapper();  
	  Account account=new Account(1,"Savings",100000);
	  Account mockAccount=new Account(1,"Savings",100000);
	  when(service.CreateAccount(any(Account.class),eq(1))).thenReturn(new Account(1,"savings",100000));
	  request=MockMvcRequestBuilders
	  .post("/api/customers/1/accounts")
	  .contentType(MediaType.APPLICATION_JSON)
	  .content(objectMapper.writeValueAsString(account));
	  MvcResult result=mockmvc.perform(request).andReturn();
	  MockHttpServletResponse response=result.getResponse();
	  Object headerval=response.getHeaderValue("location");
	  System.out.println("Response:"+ response.getContentAsString());
	  assertEquals(HttpStatus.CREATED.value(),response.getStatus());
  }
  @Test
  @Order(3)
  public void testtransfer()throws Exception{
	  Account a=new Account(3,"Savings",100000);
	  Account mockAccount=new Account();
	  mockAccount.setAccount_Number(1);
	  mockAccount.setAccountType("Savings");
	  mockAccount.setBalance(100000);
	  Account mockAccount2=new Account();
	  mockAccount2.setAccount_Number(2);
	  mockAccount2.setAccountType("Savings");
	  mockAccount2.setBalance(100000);
	  RequestBuilder request;
	  ObjectMapper objmap=new ObjectMapper();
	  Transfer transfer=new Transfer();
	  transfer.setFrom_Account_Number(1);
	  transfer.setTo_Account_Number(2);
	  transfer.setAmount(5000);
	  when(service.makeTransfer(any(Transfer.class))).thenReturn(mockAccount);
	  request=MockMvcRequestBuilders.put("/api/customers/accounts").contentType(MediaType.APPLICATION_JSON)
                   .content(objmap.writeValueAsString(a));
	  MvcResult result=mockmvc.perform(request).andReturn();
	  MockHttpServletResponse response=result.getResponse();
	  Object headerval=response.getHeaderValue("location");
	  assertEquals(HttpStatus.OK.value(),response.getStatus());
	  
  }
  @Test
  @Order(4)
  public void testgetAccountById()throws Exception{
	  RequestBuilder request;
	  int cust_id=1;
	  Account customer=new Account(1,"Saving",80000); 
	  when(service.GetAccountByID(cust_id)).thenReturn(customer);
	  request=MockMvcRequestBuilders
			  .get("/api/accounts/1")
			  .accept(MediaType.APPLICATION_JSON);
	  MvcResult result =mockmvc.perform(request)
			  .andExpect(status().isOk())
			  .andReturn();
	  MockHttpServletResponse response = result.getResponse();
	  System.out.println("Response"+response.getContentAsString());
	  System.out.println(response.getStatus()+"from service");
	  assertEquals(HttpStatus.OK.value(), response.getStatus());
  }  
}
