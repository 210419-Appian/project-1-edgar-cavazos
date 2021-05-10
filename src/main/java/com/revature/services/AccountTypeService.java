package com.revature.services;

import java.util.List;

import com.revature.daos.AccountTypeDAO;
import com.revature.daos.AccountTypeDAOImpl;
import com.revature.models.AccountType;

public class AccountTypeService {
	
	private AccountTypeDAO atDao = new AccountTypeDAOImpl();
	
	public List<AccountType> gatherAccountTypes(){
		return atDao.findAll();
	}
	
	public boolean addAccountType(AccountType a) {
		return atDao.addAccountType(a);
	}
	
	public AccountType findAccountTypeById(int id) {
		return atDao.findById(id);
	}

}
