package com.revature.daos;

import java.util.List;

import com.revature.models.Role;

public interface RoleDAO {
	
	public List<Role> findAll();
	Role findByRole(String role);
	Role findById(int id);

}
