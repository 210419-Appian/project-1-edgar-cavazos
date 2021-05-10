package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import com.revature.web.FrontControllerServlet;

public class RegisterController {
	
	private UserService uService = new UserService();
	private ObjectMapper om = new ObjectMapper();
	
	
	public void registerUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		
		
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);
		User u = om.readValue(body, User.class);
		if (uService.addUser(u)) {
			resp.setStatus(201);
			out.print("You have successfully registered");
		} else {
			out.print("Invalid fields");
			resp.setStatus(400);
		}
}
	
	
}