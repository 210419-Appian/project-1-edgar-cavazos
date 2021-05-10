package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controllers.AccountController;
import com.revature.controllers.DepositController;
import com.revature.controllers.LoginController;
import com.revature.controllers.LogoutController;
import com.revature.controllers.RegisterController;
import com.revature.controllers.TransferController;
import com.revature.controllers.UserController;
import com.revature.controllers.WithdrawController;

public class FrontControllerServlet extends HttpServlet{
	
	private String BaseURL = null;
	private UserController uControl = new UserController();
	private AccountController aControl = new AccountController();
	private RegisterController rControl = new RegisterController();
	private LoginController lControl = new LoginController();
	private LogoutController outControl = new LogoutController();
	private DepositController dControl = new DepositController();
	private WithdrawController wControl = new WithdrawController();
	private TransferController tControl = new TransferController();


	
	
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		BaseURL = config.getInitParameter("BaseURL");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		resp.setStatus(404); // default if someone sends a malformed request.

		final String URL = req.getRequestURI().replace(BaseURL, "");

		System.out.println(URL);

		// A path variable is a way to pass information about the request in the URL
		// itself. Generally you have
		// a final / in the url that takes a variable input. i.e. avenger/1 will get you
		// the first avenger while
		// avenger/2 gets the second etc.

		String[] sections = URL.split("/");
		System.out.println(sections);	
		
		
		
		
		HttpSession ses = req.getSession();
		PrintWriter out = resp.getWriter();		
		String role = (String) ses.getAttribute("role");
		
			
		
		switch (sections[0]) {	
				
		case "register":	
			if(role.equals("Employee") || role.equals("Standard")) {
				out.print("The requested action is not permitted");
				break;
			}
			else if(req.getMethod().equals("POST")) {
				rControl.registerUser(req,resp);
			}
			break;
			
			
		case "login":
			
			if(req.getMethod().equals("POST")) {
				lControl.login(req,resp);				
			}							
			break;	
					
					
		case "logout":
			
			if(req.getMethod().equals("POST")) {			
				outControl.closeSession(req, resp);
			}
			break;
			
			
		case "withdraw":
			if(role.equals("Employee")) {
				out.print("The requested action is not permitted");
				break;
			}
			
			else if(req.getMethod().equals("POST")) {				
				wControl.withdraw(req, resp);				
			}
			break;
			
			
		case "deposit":
			if(role.equals("Employee")) {
				out.print("The requested action is not permitted");
				break;
			}
			else if(req.getMethod().equals("POST")) {
				dControl.deposit(req,resp);
			}
			break;
			
		case "transfer":
			if(role.equals("Employee")) {
				out.print("The requested action is not permitted");
				break;
			}
			else if(req.getMethod().equals("POST")) {
				tControl.transfer(req, resp);
			}
			break;
			
		
		
		
		
		case "bankuser":
			
			if (req.getMethod().equals("GET")) {
				if (sections.length == 2) {
					int id = Integer.parseInt(sections[1]);
					uControl.getOneUser(resp, id);
				} else {
					if(role.equals("Standard")) {
						out.print("The requested action is not permitted");
						break;
					}
					uControl.getAllUsers(resp);
				}
			} else if (req.getMethod().equals("POST")) {				
				if(role.equals("Employee") || role.equals("Standard")) {
					out.print("The requested action is not permitted");
					break;
				}
				uControl.addUser(req, resp);
			} else if (req.getMethod().equals("PUT")&&sections.length==2) {
				uControl.putUser(req, resp);
			} else if (req.getMethod().equals("PATCH")&&sections.length==2) {
				uControl.patchUser(req, resp);
			} else if (req.getMethod().equals("DELETE")&&sections.length==2) {
				if(role.equals("Employee") || role.equals("Standard")) {
					out.print("The requested action is not permitted");
					break;
				}
				uControl.deleteUser(resp, sections[1]);
			}
			break;
			
			
			
		case "account":
			
			if (req.getMethod().equals("GET")) {
				if (sections.length == 2) {
					int id = Integer.parseInt(sections[1]);
					aControl.getOneAccount(resp, id);
				} else {
					if(role.equals("Standard")) {
						out.print("The requested action is not permitted");
						break;
					}
					aControl.getAllAccounts(resp);
				}
			}else if(req.getMethod().equals("POST")) {
				if(role.equals("Employee") || role.equals("Standard")) {
					out.print("The requested action is not permitted");
					break;
				}
				aControl.addAccount(req, resp);
			}else if (req.getMethod().equals("PUT")&&sections.length==2) {
				if(role.equals("Employee") || role.equals("Standard")) {
					out.print("The requested action is not permitted");
					break;
				}
				aControl.putAccount(req, resp);
			} else if (req.getMethod().equals("PATCH")&&sections.length==2) {
				if(role.equals("Employee") || role.equals("Standard")) {
					out.print("The requested action is not permitted");
					break;
				}
				aControl.patchAccount(req, resp);
			} else if (req.getMethod().equals("DELETE")&&sections.length==2) {
				if(role.equals("Employee") || role.equals("Standard")) {
					out.print("The requested action is not permitted");
					break;
				}
				aControl.deleteAccount(resp, sections[1]);
			}
		
			break;
			
			

		case "accountstatus":
			
			if (req.getMethod().equals("GET")) {
				if(role.equals("Standard")) {
					out.print("The requested action is not permitted");
					break;
				}
				else if (sections.length == 2) {
					int statusId = Integer.parseInt(sections[1]);
					aControl.findAllAcctByStatus(resp, statusId);
				} 			
			}
			
			
		case "accountowner":
			
			if (req.getMethod().equals("GET")) {
				if (sections.length == 2) {
					int statusId = Integer.parseInt(sections[1]);
					aControl.findAllAcctByOwner(resp, statusId);
				} 			
			}
			
		}

	}
	
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getMethod().equals("PATCH")) {
			doPatch(req, resp);
		}else {
			super.service(req, resp);
		}
	}
	

}
