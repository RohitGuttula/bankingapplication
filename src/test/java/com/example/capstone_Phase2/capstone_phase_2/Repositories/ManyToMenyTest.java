package com.example.capstone_Phase2.capstone_phase_2.Repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Account;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Customer;
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class ManyToMenyTest {
	@Autowired
	private CustomerRepository repository;
	

	@Test
	@Order(1)
	@Sql("/Many-to-Many.sql")
	void test_saveCustomer() {
		Customer firstCustomer=new Customer();
		firstCustomer.setFirst_Name("Rohit");
		firstCustomer.setLast_Name("Guttula");
		firstCustomer.setEmail("rohitguttula7@gmail.com");
		
		Customer secondCustomer=new Customer();
		secondCustomer.setFirst_Name("Bindu");
		secondCustomer.setLast_Name("Guttula");
		secondCustomer.setEmail("binduguttula7@gmail.com");
		
		Account savingAccountOne=new Account();
		savingAccountOne.setAccountType("Savings");
		savingAccountOne.setBalance(200000);
		
		Account savingAccountTwo=new Account();
		savingAccountTwo.setAccountType("Savings");
		savingAccountTwo.setBalance(300000);
		
		Account currentAccountOne=new Account();
		currentAccountOne.setAccountType("Current");
		currentAccountOne.setBalance(100000);
		
		Account currentAccountTwo=new Account();
		currentAccountTwo.setAccountType("Current");
		currentAccountTwo.setBalance(250000);
		
		Set<Account> accountsForCustomerOne=new HashSet<Account>();
		accountsForCustomerOne.add(savingAccountOne);
		accountsForCustomerOne.add(currentAccountOne);
		firstCustomer.setAccounts(accountsForCustomerOne);
		Set<Account> accountsForCustomerTwo=new HashSet<Account>();
		accountsForCustomerTwo.add(savingAccountTwo);
		accountsForCustomerTwo.add(currentAccountTwo);
		secondCustomer.setAccounts(accountsForCustomerTwo);
		
		Set<Customer> customers=new HashSet<Customer>();
		customers.add(firstCustomer);
		repository.save(firstCustomer);
		customers=null;
		customers=new HashSet<Customer>();
		customers.add(secondCustomer);
		repository.save(secondCustomer);
		
		
	}
	
	@Transactional
	@Test
	@Order(2)
	void test_getCustomer() {
		System.out.println("In test_getCustomer ");
		Iterable<Customer> customers=repository.findAll();
		Iterator<Customer> itr=customers.iterator();
		while(itr.hasNext()) {
			Customer customer=itr.next();
			System.out.println(customer);
			Set<Account> accounts=customer.getAccounts();
			System.out.println("Account Details");
			for(Account account: accounts) {
				System.out.println(account);
			}
			
		}
		
	}

}
