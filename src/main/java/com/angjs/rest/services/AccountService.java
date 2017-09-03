package com.angjs.rest.services;

import java.util.List;

import com.angjs.rest.entity.Account;
import com.angjs.rest.repos.AccountRespository;

public class AccountService {
	
	AccountRespository repo = new AccountRespository();
	
	public List<Account> getAllAccounts() {
		return repo.fetchAll();
	}

	public Account getAccountById(int id) {
		return repo.fetchById(id);
	}
	
	public String addNewAct(Account act){
		return repo.addNewAct(act);
	}
	
	
	
}
