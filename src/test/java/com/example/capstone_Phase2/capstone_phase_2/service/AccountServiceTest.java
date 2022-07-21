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

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Account;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Customer;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Transfer;
import com.example.capstone_Phase2.capstone_phase_2.Exception.AmountNotSufficient;
import com.example.capstone_Phase2.capstone_phase_2.Exception.Negativeamount;
import com.example.capstone_Phase2.capstone_phase_2.Exception.SameAccountNumbers;
import com.example.capstone_Phase2.capstone_phase_2.Repositories.AccountRepository;
import com.example.capstone_Phase2.capstone_phase_2.Repositories.CustomerRepository;
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
	@InjectMocks
	AccountService service;
	@Mock
	AccountRepository repository;
	@Mock 
	CustomerRepository repository1;
	@Test
	@Order(1)
	public void testCreateAccount() {
		Account mockAccount=new Account();
		mockAccount.setAccount_Number(1);
		mockAccount.setAccountType("Savings");
		mockAccount.setBalance(100000);
		Customer cust=new Customer(1,"Rohit","Guttula","rohitguttula7@gmail.com");
		Set<Account> account=new HashSet<Account>();
		Account acct=new Account(1,"Savings",100000);
		account.add(acct);
		Optional<Customer> cust1=Optional.of(new Customer(2,"rohi","gutt","rohi.gmail.com",account));
		when(repository1.findById(1)).thenReturn(cust1);
		when(repository.save(any(Account.class))).thenReturn(mockAccount);
		Account a=service.CreateAccount(acct,1);
		verify(repository,atLeastOnce()).save(acct);
		System.out.println(a.getAccount_Number());
		assertEquals(1,a.getAccount_Number());	
	}
	@Test
	@Order(2)
	public void testtransfer() {
		Account mockAccount=new Account();
		mockAccount.setAccount_Number(2);
		mockAccount.setAccountType("current");
		mockAccount.setBalance(80000);
		Transfer transfer=new Transfer();
		transfer.setFrom_Account_Number(1);
		transfer.setTo_Account_Number(2);
		transfer.setAmount(5000);
		Optional<Account> acct1=Optional.of(new Account(2,"current",80000));
		Optional<Account> acct2=Optional.of(new Account(1,"current",80000));
		when(repository.findById(transfer.getFrom_Account_Number())).thenReturn(acct1);
		when(repository.findById(transfer.getTo_Account_Number())).thenReturn(acct2);
		Account account1=acct1.get();
		Account account2=acct2.get();
		Account account=service.makeTransfer(transfer);
		assertEquals(85000,account2.getBalance());
	}
	@Test
	@Order(3)
	public void testtransferwithsameaccoutnumber() throws SameAccountNumbers {
		try {
		Transfer transfer=new Transfer();
		transfer.setFrom_Account_Number(1);
		transfer.setTo_Account_Number(1);
		transfer.setAmount(5000);
		Optional<Account> acct1=Optional.of(new Account(1,"savings",80000));
		Optional<Account> acct2=Optional.of(new Account(1,"savings",80000));
		when(repository.findById(transfer.getFrom_Account_Number())).thenReturn(acct1);
		when(repository.findById(transfer.getTo_Account_Number())).thenReturn(acct2);
		Account account1=acct1.get();
		Account account2=acct2.get();
		when(service.makeTransfer(transfer)).thenThrow(SameAccountNumbers.class);
		}
		catch(Exception e) {
			assertTrue(e instanceof SameAccountNumbers);
		}
		
	}
	 @Test
	@Order(4)
	public void testtransferwithzeroamount()throws Negativeamount {
		 try {
		Transfer transfer=new Transfer();
		transfer.setFrom_Account_Number(1);
		transfer.setTo_Account_Number(2);
		transfer.setAmount(0);
		Optional<Account> acct1=Optional.of(new Account(1,"savings",80000));
		Optional<Account> acct2=Optional.of(new Account(2,"savings",80000));
		when(repository.findById(transfer.getFrom_Account_Number())).thenReturn(acct1);
		when(repository.findById(transfer.getTo_Account_Number())).thenReturn(acct2);
		Account account1=acct1.get();
		Account account2=acct2.get();
		when(service.makeTransfer(transfer)).thenThrow(Negativeamount.class);
		 }
		catch(Exception e) {
			assertTrue(e instanceof Negativeamount);
		}
	}
	@Test
	@Order(5)
	public void testtransferwithfromaccountbalancelessthanamount()throws AmountNotSufficient {
		try {
		Transfer transfer=new Transfer();
		transfer.setFrom_Account_Number(1);
		transfer.setTo_Account_Number(2);
		transfer.setAmount(5000);
		Optional<Account> acct1=Optional.of(new Account(1,"savings",1000));
		Optional<Account> acct2=Optional.of(new Account(2,"savings",80000));
		when(repository.findById(transfer.getFrom_Account_Number())).thenReturn(acct1);
		when(repository.findById(transfer.getTo_Account_Number())).thenReturn(acct2);
		Account account1=acct1.get();
		Account account2=acct2.get();
		when(service.makeTransfer(transfer)).thenThrow(AmountNotSufficient.class);
		}
		catch(Exception e) {
			assertTrue(e instanceof AmountNotSufficient);
		}
	}
	@Test
	@Order(6)
	public void testgetAccount() {
		List<Account> accountList=new ArrayList<Account>();
		Account acct=new Account();
		acct.setAccount_Number(1);
		acct.setAccountType("savings");
		acct.setBalance(500000);
		accountList.add(acct);
		when(repository.findAll()).thenReturn(accountList);
		List<Account> acct1=service.GetAllAccounts();
		assertEquals(1,acct1.get(0).getAccount_Number());
	}
	@Test
	@Order(7)
	public void testGetAccountById() {
		Optional<Account> account=Optional.of(new Account(2,"Saving",80000));
		when(repository.findById(1)).thenReturn(account);
		Account acct=service.GetAccountByID(1);
		assertEquals(2,acct.getAccount_Number());
	}
	@Test
	@Order(8)
	public void testupdateAccountById() {
		Account mockAccount=new Account();
		mockAccount.setAccount_Number(2);
		mockAccount.setAccountType("Savings");
		mockAccount.setBalance(80000);
		Optional<Account> account=Optional.of(new Account(1,"Saving",80000));
		when(repository.findById(1)).thenReturn(account);
		Account acct=account.get();
		acct.setAccount_Number(mockAccount.getAccount_Number());
		acct.setAccountType(mockAccount.getAccountType());
		acct.setBalance(mockAccount.getBalance());
		when(repository.save(acct)).thenReturn(acct);
		Account a=service.updateAccount(1, acct);
		 verify(repository,atLeastOnce()).findById(1);
		 assertEquals(2,a.getAccount_Number());	
	}
	
}
