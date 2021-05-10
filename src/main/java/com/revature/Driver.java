package com.revature;

import java.util.List;

import com.revature.daos.UserDAOImpl;
import com.revature.models.User;
import com.revature.services.UserService;

public class Driver {
	
	private static UserService uService = new UserService();
	
public static void main(String[] args) {
	
		User u = uService.findUserByUserName("test1");
		System.out.println(u.getUsername());
	
}

}
