package com.example.capstone_Phase2.capstone_phase_2.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Account;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Customer;
import com.example.capstone_Phase2.capstone_phase_2.Exception.EmailalreadyPresent;
import com.example.capstone_Phase2.capstone_phase_2.Exception.SameAccountNumbers;
import com.example.capstone_Phase2.capstone_phase_2.Repositories.AccountRepository;
import com.example.capstone_Phase2.capstone_phase_2.Repositories.CustomerRepository;
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
	@InjectMocks
	CustomerService service;
	@Mock
	AccountRepository repository;
	@Mock 
	CustomerRepository repository1;
	
	@Test
	@Order(1)
	public void testCreateCustomer() {
		Customer mockCustomer=new Customer();
		mockCustomer.setCust_id(2);
		mockCustomer.setFirst_Name("Rohit");
		mockCustomer.setLast_Name("Guttula");
		mockCustomer.setEmail("rohitguttula7@gmail.com");
		Set<Account> acctset=new HashSet<Account>();
		Account account=new Account(1,"Savings",100000);
		acctset.add(account);
		mockCustomer.setAccounts(acctset);
		Customer customer=new Customer();
		customer.setCust_id(1);
		customer.setFirst_Name("Rohit1");
		customer.setLast_Name("Guttula1");
		customer.setEmail("rohitguttula1@gmail.com");
		Set<Account> acctset1=new HashSet<Account>();
		Account account1=new Account(2,"Savings",80000);
		acctset1.add(account1);
		customer.setAccounts(acctset1);
		when(repository1.save(any(Customer.class))).thenReturn(mockCustomer);
		Customer a=service.CreateCustomer(customer);
		verify(repository1,atLeastOnce()).save(customer);
		assertEquals(2,a.getCust_id());
	}
	 @Test
	 @Order(2)
	 public void testGetCustomer() {
		 List<Customer> customerList=new ArrayList<Customer>();
		 Customer cust=new Customer();
		 cust.setCust_id(1);
		 cust.setFirst_Name("Rohit");
		 cust.setLast_Name("Guttula");
		 cust.setEmail("rohitguttula7@gmail.com");
		 Set<Account> acctset=new HashSet<Account>();
		 Account account=new Account(1,"Savings",100000);
		 acctset.add(account);
		 cust.setAccounts(acctset);
		 customerList.add(cust);
		 when(repository1.findAll()).thenReturn(customerList);
		 List<Customer> customerList1=service.GetAllCustomers();
		 assertEquals(1,customerList1.get(0).getCust_id()); 
	 }
	 @Test
	 @Order(3)
	 public void testcustomerbyid() {
		 Optional<Customer> cust=Optional.of(new Customer(2,"Rohit","Guttula","rohitguttula7@gmail.com"));
		 when(repository1.findById(1)).thenReturn(cust);
		 Customer customer=service.GetCustomerByID(1);
		 assertEquals(2,customer.getCust_id());
	 }
	 @Test
	 @Order(4)
	 public void testupdateCustomerById() {
		 Customer mockcust=new Customer();
		 mockcust.setCust_id(2);
		 mockcust.setFirst_Name("Rohit");
		 mockcust.setLast_Name("Guttula");
		 mockcust.setEmail("rohitguttula7@gmail.com");
		 Set<Account> acctset=new HashSet<Account>();
		 Account account=new Account(1,"Savings",100000);
		 acctset.add(account);
		 mockcust.setAccounts(acctset);
		 Customer cust=new Customer();
		 cust.setCust_id(1);
		 cust.setFirst_Name("Rohit");
		 cust.setLast_Name("Guttula");
		 cust.setEmail("rohitguttula7@gmail.com");
		 Set<Account> acctset1=new HashSet<Account>();
		 Account account1=new Account(1,"Savings",100000);
		 acctset1.add(account1);
		 cust.setAccounts(acctset1);
		 Optional<Customer> customers=Optional.of(new Customer(1,"Rohit","Guttula","rohitguttula7@gmail.com"));
		 when(repository1.findById(1)).thenReturn(customers);
		 Customer cus=customers.get();
		 cus.setCust_id(mockcust.getCust_id());
		 cus.setFirst_Name(mockcust.getFirst_Name());
		 cus.setLast_Name(mockcust.getLast_Name());
		 cus.setEmail(mockcust.getEmail());
		 cus.setAccounts(mockcust.getAccounts());
		 when(repository1.save(cus)).thenReturn(cus);
		 Customer c=service.updateCustomer(cust, 1);
		 verify(repository1,atLeastOnce()).findById(1);
		 assertEquals(2,c.getCust_id());
	 }
	 
		
	

}
