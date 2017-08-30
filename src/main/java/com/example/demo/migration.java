package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class migration {
	
	
	
	public migration(String svn, String git) {
		super();
		this.svn = svn;
		this.git = git;
	}
	public migration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public migration(String svn, String email, String git, String date) {
		super();
		this.svn = svn;
		this.email = email;
		this.git = git;
		this.date = date;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	String svn;
	
	String git;
	String email;
	String date;
	public String getSvn() {
		return svn;
	}
	public void setSvn(String svn) {
		this.svn = svn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGit() {
		return git;
	}
	public void setGit(String git) {
		this.git = git;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	



}