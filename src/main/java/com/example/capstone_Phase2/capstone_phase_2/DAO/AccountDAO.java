package com.example.capstone_Phase2.capstone_phase_2.DAO;

import java.util.List;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Account;



public interface AccountDAO {
	
	public Account CreateAccount(Account account);
	public Account GetAccountByID(Integer accountNumber);
	public List<Account> GetAllAccounts();
	public Account updateAccount(Integer accountNumber,Account account);
	public Account deleteAccount(Integer accountNumber);
	

}
