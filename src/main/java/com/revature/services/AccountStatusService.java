package com.revature.services;

import java.util.List;

import com.revature.daos.AccountStatusDAO;
import com.revature.daos.AccountStatusDAOImpl;
import com.revature.models.AccountStatus;

public class AccountStatusService {
	
	private AccountStatusDAO asDao = new AccountStatusDAOImpl();
	
	public List<AccountStatus> gatherAccountStatus(){
		return asDao.findAll();
	}
	
	public boolean addAccountStatus(AccountStatus as) {
		return asDao.addAccountStatus(as);
	}
	
	public AccountStatus findAccountStatusById(int id) {
		return asDao.findById(id);
	}

}
