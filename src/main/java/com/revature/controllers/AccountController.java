package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
import com.revature.services.AccountService;

public class AccountController {
	
	private AccountService aService = new AccountService();
	private ObjectMapper om = new ObjectMapper();

	public void getAllAccounts(HttpServletResponse resp) throws IOException {

		List<Account> list = aService.findAllAccounts();
		
		String json = om.writeValueAsString(list);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		
	}

	public void getOneAccount(HttpServletResponse resp, int id) throws IOException {
		
		Account a = aService.findAccountById(id);

		String json = om.writeValueAsString(a);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		
	}

	public void addAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);
		Account a = om.readValue(body, Account.class);
		if (aService.addAccount(a)) {
			resp.setStatus(201);
		} else {
			resp.setStatus(406);
		}

	}
	
	public void putAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);
		Account a = om.readValue(body, Account.class);

		if(aService.updateFullAccount(a)) {
			out.print("Account #"+a.getAccountId()+" Has been updated.");
			resp.setStatus(200);
		}else {
			out.print("Failed to update account.");
			resp.setStatus(400);
		}
	}
	
	public void patchAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);
		Account a = om.readValue(body, Account.class);

		if(aService.updatePartialAccount(a)) {
			resp.setStatus(200);
		}else {
			resp.setStatus(400);
		}
		
	}
	
	public void deleteAccount(HttpServletResponse resp, String string) throws IOException {
		try {
			int id = Integer.parseInt(string);
			if(aService.deleteSingleAccount(id)) {
				resp.setStatus(204);
			}else {
				resp.setStatus(400);
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
			resp.setStatus(418);
		}
		
	}
	
	public void findAllAcctByStatus(HttpServletResponse resp, int statusId) throws IOException {		

		List<Account> list = aService.findAllAccountsByStatus(statusId);
		
		String json = om.writeValueAsString(list);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		
	}
	
	
	public void findAllAcctByOwner(HttpServletResponse resp, int ownerId) throws IOException {		

		List<Account> list = aService.findAllAccountsByOwnerId(ownerId);
		
		String json = om.writeValueAsString(list);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		
	}
	

}
