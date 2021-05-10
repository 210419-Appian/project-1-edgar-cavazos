 package com.revature.models;

public class LoginDTO {
	
	/* 
	 * DTO stands for Data transfer object. They are temporary objects that are used to store information coming from outside 
	 * your application, if that information does not perfectly fit any already existing object type in your application
	 */
	
	public String username;
	public String password;
	
	public LoginDTO() {
		super();
	}
	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
