package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController {
	
	
	public void closeSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		HttpSession ses = req.getSession(false);
		String username = (String) ses.getAttribute("username");
		
		if(ses != null) {
			ses.invalidate();
			out.print("You have successfully logged out "+username);
			resp.setStatus(200);
		} 
//		else					//dead code
//			out.print("There was no user logged into the session");
//			resp.setStatus(400);
		}
		
	}
	


