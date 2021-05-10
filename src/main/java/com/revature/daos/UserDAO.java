package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	List<User> findAll();
	public User findById(int id);
	User findByUsername(String username);
	boolean add(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(int id);	
}
