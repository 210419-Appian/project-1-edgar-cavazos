package com.revature.daos;

import java.util.List;

import com.revature.models.AccountType;

public interface AccountTypeDAO {
	
	public List<AccountType> findAll();
	public boolean addAccountType(AccountType a);
	public AccountType findById(int id);

}
