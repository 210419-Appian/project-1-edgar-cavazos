package com.revature.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.DepositDTO;
import com.revature.services.AccountService;

public class DepositController {	 
	
	public void deposit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		AccountService aService = new AccountService();		
		ObjectMapper om = new ObjectMapper();
		PrintWriter out = resp.getWriter();	
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		
		String body = new String(sb);
		DepositDTO a = om.readValue(body, DepositDTO.class);		
			
	
		if(aService.depositTransaction(a.getAmount(), a.getAccountId())) {
			resp.setStatus(200);
			out.print("$"+a.getAmount()+" has been deposited from Account #"+a.getAccountId());
			resp.setContentType("application/json");
		}else {
			resp.setStatus(400);
			out.print("Deposit Request failed");
			resp.setContentType("application/json");
		}
	}
}
