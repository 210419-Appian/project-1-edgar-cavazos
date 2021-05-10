package com.revature.daos;

import java.util.List;

import com.revature.models.AccountStatus;

public interface AccountStatusDAO {
	
	public List<AccountStatus> findAll();
	public boolean addAccountStatus(AccountStatus as);
	public AccountStatus findById(int id);

}
