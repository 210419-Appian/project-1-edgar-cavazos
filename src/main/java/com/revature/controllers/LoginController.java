package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.UserService;

public class LoginController {
	
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
	UserService uService = new UserService(); 
	ObjectMapper om = new ObjectMapper();
	PrintWriter out = resp.getWriter();	
	
	BufferedReader reader = req.getReader();
	StringBuilder sb = new StringBuilder();
	String line = reader.readLine();

	while (line != null) {
		sb.append(line);
		line = reader.readLine();
	}	

	String body = new String(sb);
	LoginDTO a = om.readValue(body, LoginDTO.class);
	
	User u = uService.findUserByUserName(a.getUsername());
	
	if(u.getUsername().equals(a.getUsername()) && u.getPassword().equals(a.getPassword())) {
		HttpSession ses = req.getSession();
		ses.setAttribute("username", a.getUsername());
		ses.setAttribute("role", u.getUserRole().getRole());
		ses.setAttribute("userId", u.getUserId());
		resp.setStatus(200);
		out.print(a.getUsername()+" has logged in \n");
		out.print("Role: "+u.getUserRole().getRole()+"\n");
		out.print("User Id:"+u.getUserId());
	
	}else {
		
		out.print("Invalid Credentials");
		resp.setStatus(400);
	}
	
	
	}

}
