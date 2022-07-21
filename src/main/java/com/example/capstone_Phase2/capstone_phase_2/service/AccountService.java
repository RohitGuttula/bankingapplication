package com.example.capstone_Phase2.capstone_phase_2.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.capstone_Phase2.capstone_phase_2.DAO.AccountDAO;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Account;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Customer;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Transfer;
import com.example.capstone_Phase2.capstone_phase_2.Exception.AccountNotFound;
import com.example.capstone_Phase2.capstone_phase_2.Exception.AmountNotSufficient;
import com.example.capstone_Phase2.capstone_phase_2.Exception.CustNotFound;
import com.example.capstone_Phase2.capstone_phase_2.Exception.Negativeamount;
import com.example.capstone_Phase2.capstone_phase_2.Exception.SameAccountNumbers;
import com.example.capstone_Phase2.capstone_phase_2.Repositories.AccountRepository;
import com.example.capstone_Phase2.capstone_phase_2.Repositories.CustomerRepository;


@Service
public class AccountService {
	
	@Autowired
	AccountRepository repository;
	@Autowired
	CustomerRepository repository1;
	
	public Account CreateAccount(Account account,Integer custId) {
		Optional<Customer> customers=repository1.findById(custId);
		if(customers.isEmpty()) {
			throw new AccountNotFound("Customer With ->"+custId+"Not found");
		}
		else {
		Account createdAccount= repository.save(account);
		return createdAccount;
		}
	}
	public Account GetAccountByID(Integer accountNumber) {
		Optional<Account> accounts=repository.findById(accountNumber);
		if(accounts.isEmpty()) {
			throw new AccountNotFound("Account With ->"+accountNumber+"Not found");
		}
		Account account=accounts.get();
		if(account==null) {
			throw new AccountNotFound("Account With ->"+accountNumber+"Not found");
		}
		return account;
	}
	public List<Account> GetAllAccounts(){
		
		List<Account> accounts= (List<Account>) repository.findAll();
		return accounts;
	}
	public Account updateAccount(Integer accountNumber,Account account) {
		Optional<Account> accounts=repository.findById(accountNumber);
		if(accounts.isEmpty()) {
			throw new AccountNotFound("Account With ->"+accountNumber+"Not found");
		}
		Account acct=accounts.get();
		if(acct==null) {
			throw new AccountNotFound("Account With ->"+accountNumber+"Not found");
		}
		acct.setAccountType(account.getAccountType());
		acct.setBalance(account.getBalance());
		Account updateAccount=repository.save(acct);
		
		return updateAccount;
	}
	public Account deleteAccount(Integer accountNumber) {
		Optional<Account> accounts=repository.findById(accountNumber);
		if(accounts.isEmpty()) {
			throw new AccountNotFound("Account With ->"+accountNumber+"Not found");
		}
		Account deletebyid=accounts.get();
		if(deletebyid==null) {
			throw new AccountNotFound("Account With ->"+accountNumber+"Not found");
		}
		repository.deleteById(accountNumber);
		return deletebyid;
	}
	public Account updatedetailswithCustomerId(Integer cust_id, Account account) {
		Optional<Customer> customers=repository1.findById(cust_id);
		if(customers.isEmpty()) {
			throw new CustNotFound ("Customer With ->"+cust_id+"Not found");
		}
		else {
			Optional<Account> accounts=repository.findById(cust_id);
			if(accounts.isEmpty()) {
				throw new AccountNotFound("Account With ->"+cust_id+"Not found");
			}
			Account acct=accounts.get();
			if(acct==null) {
				throw new AccountNotFound("Account With ->"+cust_id+"Not found");
			}
			acct.setAccountType(account.getAccountType());
			acct.setBalance(account.getBalance());
			Account updateAccount=repository.save(acct);
			return updateAccount;
		}
	}
	public Customer GetDetails(Integer cust_id) {
		Optional<Customer> customers=repository1.findById(cust_id);
		if(customers.isEmpty()) {
			throw new CustNotFound("Customer With ->"+cust_id+"Not found");
		}
		Customer customer=customers.get();
		if(customer==null) {
			throw new CustNotFound("Customer With ->"+cust_id+"Not found");
		}
		return customer;
	}
	@Transactional
	public Account makeTransaction(Account account,Integer fromAccount, Integer toAccount, Integer amount) {
		Optional<Account> accounts1=repository.findById(fromAccount);
		Optional<Account> accounts2=repository.findById(toAccount);
		if(accounts1.isEmpty()) {
			throw new AccountNotFound("Account with ->"+fromAccount+"Not Found");
		}
		if(accounts2.isEmpty()) {
			throw new AccountNotFound("Account with ->"+toAccount+"Not Found");
		}
		Account acct1=accounts1.get();
		Account acct2=accounts2.get();
		if(acct1==null) { 
			throw new AccountNotFound("Account With ->"+fromAccount+"Not found");
		}
		if(acct2==null) {
			throw new AccountNotFound("Account With ->"+toAccount+"Not found");
		}
		Integer sourceBalance=acct1.getBalance();
		Integer receiverBalance=acct2.getBalance();
		if(sourceBalance<amount) {
			System.out.println("Not Sufficient Balance");
		}
		else {
			sourceBalance=sourceBalance-amount;
			System.out.println(sourceBalance);
			receiverBalance=receiverBalance+amount;
			System.out.println(receiverBalance);
		}
		acct1.setBalance(sourceBalance);
		acct1.setAccount_Number(fromAccount);
		acct2.setAccountType(acct1.getAccountType());
		acct2.setBalance(receiverBalance);
		acct2.setAccount_Number(toAccount);
		Account accounttransaction=repository.save(acct1);
		accounttransaction=repository.save(acct2);
		return accounttransaction;  
	}
	@Transactional
	public Account makeTransfer(Transfer transfer) {
		Integer from_account=transfer.getFrom_Account_Number();
		Integer to_account=transfer.getTo_Account_Number();
		Integer amount=transfer.getAmount();
		Optional<Account> accounts1=repository.findById(from_account);
		Optional<Account> accounts2=repository.findById(to_account);
		if(accounts1.isEmpty()) {
			throw new AccountNotFound("Account with ->"+from_account+"Not Found");
		}
		if(accounts2.isEmpty()) {
			throw new AccountNotFound("Account with ->"+to_account+"Not Found");
		}
		Account acct1=accounts1.get();
		Account acct2=accounts2.get();
		if(acct1==null) { 
			throw new AccountNotFound("Account With ->"+from_account+"Not found");
		}
		if(acct2==null) {
			throw new AccountNotFound("Account With ->"+to_account+"Not found");
		}
		Integer sourceBalance=acct1.getBalance();
		Integer receiverBalance=acct2.getBalance();
		if(sourceBalance<amount) {
			throw new AmountNotSufficient("Source Account Balance "+acct1.getBalance()+" should not less than "+amount);
		}
		else if(acct1.getAccount_Number()==acct2.getAccount_Number()) {
			throw new SameAccountNumbers("Source Account "+acct1.getAccount_Number()+" to Account "+acct2.getAccount_Number()+" should not be same");
		}
		else if(amount<=0) {
			throw new Negativeamount("Amount should not be negative or Zero");
		}
		else {
			sourceBalance=sourceBalance-amount;
			System.out.println(sourceBalance);
			receiverBalance=receiverBalance+amount;
			System.out.println(receiverBalance);
		}
		acct1.setBalance(sourceBalance);
		acct1.setAccount_Number(from_account);
		acct2.setAccountType(acct1.getAccountType());
		acct2.setBalance(receiverBalance);
		acct2.setAccount_Number(to_account);
		Account accounttransaction=repository.save(acct1);
		accounttransaction=repository.save(acct2);
		return accounttransaction;
		
	}
	
	
}
