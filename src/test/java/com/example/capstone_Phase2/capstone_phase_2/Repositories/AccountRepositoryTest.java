package com.example.capstone_Phase2.capstone_phase_2.Repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Account;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Transfer;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
@SpringBootTest
class AccountRepositoryTest {
	@Autowired
	AccountRepository repository;

	@Test
	@Order(1)
	void testSaveAccount() {
		Account acct=new Account("Current",100000);
		repository.save(acct);
		Account acct1=new Account("Savings",50000);
		repository.save(acct1);
		Account acct2=new Account("current",75000);
		repository.save(acct2);
	}
	@Test
	@Order(2)
	void testGetAccount() {
		Account acct=new Account("Current",100000);
		repository.save(acct);
		Optional<Account> acctnum=repository.findById(acct.getAccount_Number());
		System.out.println("Account Details"+acctnum);
		assertEquals("Current",acct.getAccountType());
	}
	
	@Test
	@Order(9)
	void testGetAccounts() {
		Iterable<Account> accounts=repository.findAll();
		for(Account account:accounts) {
			System.out.println("Account Details"+account);
		}
		
	}
	@Test
	@Order(3)
	void testDeleteEmployee() {
		Account acct=new Account("Savings",10000);
		repository.save(acct);
		System.out.println("Delete test");
		repository.deleteById(acct.getAccount_Number());
	}
	@Test
	@Order(4)
	void testUpdateAccount() {
		Account acct=new Account("cur",20000);
		repository.save(acct);
		Optional<Account> accounts=repository.findById(acct.getAccount_Number());
		Account account=accounts.get();
		account.setAccountType("Current");
		account.setBalance(10000);
		System.out.println("In update test");
		repository.save(account);
	}
	@Test
	@Order(5)
	void testfindByBalance() {
		Account accounts=repository.findByBalance(75000);
		assertEquals(4,accounts.getAccount_Number());
	}	
	@Test
	@Order(6)
	void testfindByAccountTypeAndBalance() {
		Account account=repository.findByAccountTypeAndBalance("Current", 100000);
		System.out.println("In testfindByAccountTypeAndBalance");
		System.out.println("Account Details:"+account);
	}
	@Test
	@Order(7)
	void testfindByAccountTypeOrBalance() {
		List<Account> accounts=repository.findByAccountTypeOrBalance("Current", 100000);
		System.out.println("in testfindByAccountTypeOrBalance");
		for(Account account: accounts) {
			System.out.println("Account Details"+account);
		}
		
	}
	@Test
	@Order(8)
	void testgetAllAccounts() {
		System.out.println("In test Get all accounts");
		List<Account> accounts=repository.getAllAccounts();
		for(Account account:accounts) {
			System.out.println("Account Details"+account);
		}
	}
	@Transactional
    @Test
    @Order(3)
    void test_Transfer() {
        System.out.println("In test_update_customer");
        Account firstCustomer=new Account();
        firstCustomer.setAccount_Number(1);
        firstCustomer.setAccountType("Nobita");
        firstCustomer.setBalance(9000);
        Account firstCustomer1=new Account();
        firstCustomer1.setAccount_Number(2);
        firstCustomer1.setAccountType("Nobi");
        firstCustomer1.setBalance(9000);
        repository.save(firstCustomer);
        repository.save(firstCustomer1);

        Transfer t=new Transfer();
        t.setFrom_Account_Number(1);
        t.setTo_Account_Number(2);
        t.setAmount(5000);
        int x=t.getFrom_Account_Number();
        if((x==firstCustomer.getAccount_Number())&&(firstCustomer.getBalance()>t.getAmount()))
                {
                    int balance1=firstCustomer.getBalance()-t.getAmount();
                    firstCustomer.setBalance(balance1);
                    repository.save(firstCustomer);
                    int balance2=firstCustomer1.getBalance()+t.getAmount();
                    firstCustomer1.setBalance(balance2);
                    repository.save(firstCustomer1);
                    assertEquals(firstCustomer1.getBalance(),14000);

                }
        if((x==firstCustomer1.getAccount_Number())&&(firstCustomer1.getBalance()>t.getAmount()))
        {
            int balance1=firstCustomer1.getBalance()-t.getAmount();
            firstCustomer1.setBalance(balance1);
            repository.save(firstCustomer1);
            int balance2=firstCustomer.getBalance()+t.getAmount();
            firstCustomer.setBalance(balance2);
            repository.save(firstCustomer1);

        }

}
}
