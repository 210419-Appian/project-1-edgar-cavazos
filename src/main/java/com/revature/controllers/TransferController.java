package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.TransferDTO;
import com.revature.services.AccountService;

public class TransferController {
	

public void transfer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		AccountService aService = new AccountService();
		ObjectMapper om = new ObjectMapper();
		PrintWriter out = resp.getWriter();
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		 String body = new String (sb);
		 TransferDTO a = om.readValue(body, TransferDTO.class);
				
		
		if(aService.transferTransaction(a.getSourceAccountId(), a.getTargetAccountId(), a.getAmount())) {
			resp.setStatus(200);
			out.print("$"+a.getAmount()+" has been transferred from Account #"+a.getSourceAccountId()+" to Account#"+a.getTargetAccountId());
			resp.setContentType("application/json");
		}else {
			resp.setStatus(400);
			out.print("Transfer Request failed");
			resp.setContentType("application/json");
		}
	}

}
 