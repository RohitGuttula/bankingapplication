package com.example.capstone_Phase2.capstone_phase_2.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Account;

public interface AccountRepository extends CrudRepository<Account,Integer> {
	
	//public List<Account> findByAccountType(String account_Type);
	public Account findByBalance(Integer balance);
//	@Query("from Account where accountType=:accountType")
//	public List<Account> getAccountByType(String accountType);
//	@Query("from Account where balace=:balance")
//	public Account getBalance(Integer balance);
	public Account findByAccountTypeAndBalance(String accountType,Integer balance);
	public List<Account> findByAccountTypeOrBalance(String accountType,Integer balance);
	@Query(value="select * from Account",nativeQuery=true)
	public List<Account> getAllAccounts();

}
