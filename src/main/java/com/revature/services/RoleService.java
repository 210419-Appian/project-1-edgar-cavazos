package com.revature.services;

import java.util.List;

import com.revature.daos.RoleDAO;
import com.revature.daos.RoleDAOImpl;
import com.revature.models.Role;

public class RoleService {
	
private RoleDAO roleDAO = new RoleDAOImpl();

public List<Role> gatherRole(){
	return roleDAO.findAll();
}

public Role findRoleById(int id) {
	return roleDAO.findById(id);
}

}
