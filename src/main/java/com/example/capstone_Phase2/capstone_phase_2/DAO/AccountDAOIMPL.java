package com.example.capstone_Phase2.capstone_phase_2.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Account;
@Component


public class AccountDAOIMPL implements AccountDAO {
	
	 private static List<Account> accounts=new ArrayList<Account>();
	    static int counter= 1000;
	    static {
	    	accounts.add(new Account(++counter,"Current",100000));
	    	accounts.add(new Account(++counter,"savings",120000));
	    	accounts.add(new Account(++counter,"savings",130000));
	    }
	    
		@Override
		public Account CreateAccount(Account account) {
			Account saveAccount=account;
			saveAccount.setAccount_Number(++counter);
			accounts.add(saveAccount);
			// TODO Auto-generated method stub
			return saveAccount;
		}

		@Override
		public Account GetAccountByID(Integer accountNumber) {
			//System.out.println(accountNumber);
			Account tempaccountNumber=null;
			for(Account account:accounts) {
				if(account.getAccount_Number().equals(accountNumber)) {
					System.out.println(accountNumber);
					tempaccountNumber=account;
					break;
				}
			}
			// TODO Auto-generated method stub
			return tempaccountNumber;
		}

		@Override
		public List<Account> GetAllAccounts() {
			// TODO Auto-generated method stub
			return accounts;
		}

		@Override
		public Account updateAccount(Integer accountNumber, Account acc) {
			
			Account tempaccountNumber=null;
			for(Account account:accounts) {
				if(account.getAccount_Number().equals(accountNumber)) {
					account.setAccountType(acc.getAccountType());
					account.setBalance(acc.getBalance());
					tempaccountNumber=account;
					break;
				}
			}
			// TODO Auto-generated method stub
			return tempaccountNumber;
		}

		@Override
		public Account deleteAccount(Integer accountNumber) {
			Account tempaccountNumber=null;
			Iterator<Account> itr=accounts.iterator();
			while(itr.hasNext()) {
				Account act=itr.next();
				if(act.getAccount_Number().equals(accountNumber)) {
					tempaccountNumber=act;
					itr.remove();
				}
			}
			// TODO Auto-generated method stub
			return tempaccountNumber;
		}

		

		
}
