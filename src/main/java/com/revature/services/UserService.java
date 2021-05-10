package com.revature.services;

import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class UserService {

	private UserDAO uDAO = new UserDAOImpl();

	public List<User> findAllUsers() {
		List<User> list = uDAO.findAll();

		return list;
	}

	public User findUserById(int id) {
		return uDAO.findById(id);
	}

	public User findUserByUserName(String username) {
		return uDAO.findByUsername(username);
	}	

	public boolean addUser(User user) {
		return uDAO.add(user);
	}
	
	public boolean deleteSingleUser(int id) {
		return uDAO.deleteUser(id);
	}

	public boolean updateFullUser(User user) {
		return uDAO.updateUser(user);
	}

	public boolean updatePartialUser(User user) {
		
		if (user.getUserId() == 0) {
			return false;
		}

		User u = findUserById(user.getUserId());

		if (user.getUsername() == null) {
			user.setUsername(u.getUsername());
		}
		if (user.getPassword() == null) {
			user.setPassword(u.getPassword());
		}
		if (user.getFirstName() == null) {
			user.setFirstName(u.getFirstName());
		}
		if (user.getLastName() == null) {
			user.setLastName(u.getLastName());
		}
		if (user.getEmail() == null) {
			user.setEmail(u.getEmail());
		}
		if (user.getUserRole() == null) {
			user.setUserRole(u.getUserRole());
		}

		return uDAO.updateUser(user);
	}

}
