package com.example.capstone_Phase2.capstone_phase_2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Account;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Customer;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Transfer;
import com.example.capstone_Phase2.capstone_phase_2.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	AccountService service;
	@PostMapping("/api/customers/{cust_id}/accounts")
	public ResponseEntity<Account> CreateAccount(@RequestBody Account account,@PathVariable Integer cust_id) {
		 Account a=(service.CreateAccount(account,cust_id));
		 return new ResponseEntity<>(a,HttpStatus.CREATED);
	}
	
	@PutMapping("/api/customers/{cust_id}/accounts")
	public ResponseEntity<Account> updatedetailswithCustomerId(@PathVariable Integer cust_id, @RequestBody Account account) {
		Account updatedetailswithCustomerId=service.updatedetailswithCustomerId(cust_id,account);
		return new ResponseEntity<>(updatedetailswithCustomerId,HttpStatus.OK);
	}
	
	@PutMapping("/api/customers/accounts")
	public ResponseEntity<Account> transferfund(@RequestBody Transfer transfer) {
		Account makeTransaction=service.makeTransfer(transfer);
		return new ResponseEntity<>(makeTransaction,HttpStatus.OK);
	}
	/*@PutMapping("/api/customers/accounts/{fromAccount}/{toAccount}/{amount}")
	public Account makeTransaction(@RequestBody Account account,@PathVariable Integer fromAccount,@PathVariable Integer toAccount,@PathVariable Integer amount){
			Account makeTransaction=service.makeTransaction(account,fromAccount,toAccount,amount); 
			return makeTransaction;
	}*/
	@GetMapping("/api/accounts/{accountNumber}")
	public ResponseEntity<Account> GetAccountByID(@PathVariable Integer accountNumber) {
		Account account=service.GetAccountByID(accountNumber);
		return new ResponseEntity<>(account,HttpStatus.OK);
	}
	
	@GetMapping("/api/accounts")
	public List<Account> GetAllAccounts(){
		List<Account> accounts=service.GetAllAccounts();
		return accounts;	
	}
	@PutMapping("/api/accounts/{accountNumber}")
	public Account updateAccount(@PathVariable Integer accountNumber,@RequestBody Account account) {
		Account updateAccount=service.updateAccount(accountNumber, account);
		return updateAccount;
		
	}
	
}
