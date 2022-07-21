package com.example.capstone_Phase2.capstone_phase_2.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

import com.example.capstone_Phase2.capstone_phase_2.Entity.Account;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Customer;
import com.example.capstone_Phase2.capstone_phase_2.Repositories.CustomerRepository;
import com.example.capstone_Phase2.capstone_phase_2.service.CustomerService;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(SpringExtension.class) //@RunsWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)@TestMethodOrder(OrderAnnotation.class)
class CustomerControllerTest {

	 @Autowired
	  private MockMvc mockmvc;
	 @Mock
	 CustomerRepository repository;
	 @MockBean
	 private CustomerService service;
	 @InjectMocks
	 CustomerController controller;
	 
	 @Test
	  @Order(1) 
	  void createAccount() throws Exception {
		  RequestBuilder request;
		  ObjectMapper objectMapper=new ObjectMapper();  
		  Customer firstCustomer=new Customer();
		    firstCustomer.setCust_id(1);
			firstCustomer.setFirst_Name("Rohit");
			firstCustomer.setLast_Name("Guttula");
			firstCustomer.setEmail("rohitguttula7@gmail.com");
			Account savingAccountOne=new Account();
			savingAccountOne.setAccount_Number(1);
			savingAccountOne.setAccountType("Savings");
			savingAccountOne.setBalance(200000);
			Account currentAccountOne=new Account();
			currentAccountOne.setAccount_Number(2);
			currentAccountOne.setAccountType("Current");
			currentAccountOne.setBalance(100000);
			Set<Account> accountsForCustomerOne=new HashSet<Account>();
			accountsForCustomerOne.add(savingAccountOne);
			accountsForCustomerOne.add(currentAccountOne);
			firstCustomer.setAccounts(accountsForCustomerOne);
			Set<Customer> customers=new HashSet<Customer>();
			customers.add(firstCustomer);
		  Customer mockCustomer=new Customer();
		  mockCustomer.setCust_id(1);
		  mockCustomer.setFirst_Name("Rohit");
		  mockCustomer.setLast_Name("Guttula");
		  mockCustomer.setEmail("rohitguttula7@gmail.com");
			Account savingAccountOnemock=new Account();
			savingAccountOnemock.setAccount_Number(1);
			savingAccountOnemock.setAccountType("Savings");
			savingAccountOnemock.setBalance(200000);
			Account currentAccountOnemock=new Account();
			currentAccountOnemock.setAccount_Number(2);
			currentAccountOnemock.setAccountType("Current");
			currentAccountOne.setBalance(100000);
			Set<Account> accountsForCustomerOnemock=new HashSet<Account>();
			accountsForCustomerOne.add(savingAccountOnemock);
			accountsForCustomerOne.add(currentAccountOnemock);
			mockCustomer.setAccounts(accountsForCustomerOnemock);
		  when(service.CreateCustomer(any(Customer.class))).thenReturn(new Customer(2,"Rohit","Guttula","rohitguttula7@gmail.com"));
		  request=MockMvcRequestBuilders
		  .post("/api/customers")
		  .contentType(MediaType.APPLICATION_JSON)
		  .content(objectMapper.writeValueAsString(firstCustomer));
		  MvcResult result=mockmvc.perform(request).andReturn();
		  MockHttpServletResponse response=result.getResponse();
		  Object headerval=response.getHeaderValue("location");
		  System.out.println("Response:"+ response.getContentAsString());
		  System.out.println(response.getStatus());
		  assertEquals(HttpStatus.CREATED.value(),response.getStatus());
	  }
	 @Test
	 @Order(2)
	 public void testGetAllCustomers() throws Exception{
		 RequestBuilder request;
		 List<Customer> custList=new ArrayList<Customer>();
		 Customer firstCustomer=new Customer();
		    firstCustomer.setCust_id(1);
			firstCustomer.setFirst_Name("Rohit");
			firstCustomer.setLast_Name("Guttula");
			firstCustomer.setEmail("rohitguttula7@gmail.com");
			Set<Account> acctset=new HashSet<Account>();
			Account account=new Account(1,"Savings",100000);
			acctset.add(account);
			firstCustomer.setAccounts(acctset);
			custList.add(firstCustomer);
 		  when(service.GetAllCustomers()).thenReturn(custList);
		  request=MockMvcRequestBuilders
				  .get("/api/customers")
				  .accept(MediaType.APPLICATION_JSON);
		  //String expectedResult="{cust_id: 1,first_Name: Rohit,last_Name: Guttula,email:RohitGuttula@gmail.com,accounts=[account_Number: 1,accountType: Savings,balance: 200000},{account_Number: 2,accountType:Current,balance: 100000}]}";
		  //System.out.println(expectedResult);
		  MvcResult result =mockmvc.perform(request)
				  .andExpect(status().isOk())
				  .andReturn();
		  MockHttpServletResponse response = result.getResponse();
		  System.out.println(response.getStatus()+"from service");
		  //System.out.println(expectedResult);
		  assertEquals(HttpStatus.OK.value(), response.getStatus());
	 }
	 @Test
	 @Order(3)
	 public void testGetCustomerById() throws Exception {
		  RequestBuilder request;
		  int cust_id=1;
		  Customer customer=new Customer(1,"Rohit","guttula","rohitguttula7@gmail.com"); 
		  when(service.GetCustomerByID(cust_id)).thenReturn(customer);
		  request=MockMvcRequestBuilders
				  .get("/api/customers/1")
				  .accept(MediaType.APPLICATION_JSON);
		  MvcResult result =mockmvc.perform(request)
				  .andExpect(status().isOk())
				  .andReturn();
		  MockHttpServletResponse response = result.getResponse();
		  System.out.println("Response"+response.getContentAsString());
		  System.out.println(response.getStatus()+"from service");
		  assertEquals(HttpStatus.OK.value(), response.getStatus());
	 }
	 
	 @Test
	 @Order(4)
	 public void testUpdateCustomer() throws Exception {
		 RequestBuilder request;
		 ObjectMapper objectmapper=new ObjectMapper();
		 Customer customer=new Customer(1,"Rohit","guttula","rohitguttula7@gmail.com");
		 Customer mockCustomer=new Customer(1,"Rohit","guttula","rohitguttula7@gmail.com");
		 when(service.updateCustomer(customer, 1)).thenReturn(new Customer(1,"rohit","guttula","rohitguttula7@gmail.com"));
		 request=MockMvcRequestBuilders.put("/api/customers/1")
				                       .contentType(MediaType.APPLICATION_JSON)
				                       .content(objectmapper.writeValueAsString(customer));
		 MvcResult result=mockmvc.perform(request).andReturn();
		 System.out.println(result.getResponse().getContentAsString());
		 MockHttpServletResponse response=result.getResponse();
		 Object headervalue=response.getHeaderValue("Location");
		 assertEquals(HttpStatus.OK.value(),response.getStatus());
	 }   
}
