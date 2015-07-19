package com.test;

import java.util.Date;


public class User {
	private String username;
	private String userIP;
	private Date linkDate;
	public User(String username, String userIP, Date linkDate) {
		this.username = username;
		this.userIP = userIP;
		this.linkDate = linkDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	public Date getLinkDate() {
		return linkDate;
	}
	public void setLinkDate(Date linkDate) {
		this.linkDate = linkDate;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", userIP=" + userIP + ", linkDate=" + linkDate + "]";
	}

}
