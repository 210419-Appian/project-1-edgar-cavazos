package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserController {
	
	private UserService uService = new UserService();
	private ObjectMapper om = new ObjectMapper();
	
	public void getAllUsers(HttpServletResponse resp) throws IOException {
		
		List<User> list = uService.findAllUsers();
		
		String json = om.writeValueAsString(list);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		resp.setContentType("application/json");
		
	}
	
	public void getOneUser(HttpServletResponse resp, int id) throws IOException {
		
		User u = uService.findUserById(id);

		String json = om.writeValueAsString(u);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		resp.setContentType("application/json");
	}
	
	public void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
			resp.setContentType("application/json");
		} else {
			resp.setStatus(406);
			resp.setContentType("application/json");
		}

	}

	public void putUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
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

		if(uService.updateFullUser(u)) {
			resp.setStatus(200);
			out.print(u.getUsername()+" Has been Updated");
			resp.setContentType("application/json");
		}else {
			resp.setStatus(400);
			out.print("Update request failed");
			resp.setContentType("application/json");
		}
	}

	public void patchUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);
		User u = om.readValue(body, User.class);

		if(uService.updatePartialUser(u)) {
			resp.setStatus(200);
			resp.setContentType("application/json");
		}else {
			resp.setStatus(400);
			resp.setContentType("application/json");
		}
		
	}

	public void deleteUser(HttpServletResponse resp, String string) throws IOException {
		try {
			int id = Integer.parseInt(string);
			if(uService.deleteSingleUser(id)) {
				resp.setStatus(204);
				resp.setContentType("application/json");
			}else {
				resp.setStatus(400);
				resp.setContentType("application/json");
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
			resp.setStatus(418);
			resp.setContentType("application/json");
		}
		
	}
}
