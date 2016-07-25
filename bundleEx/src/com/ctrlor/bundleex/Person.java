package com.ctrlor.bundleex;

import java.io.Serializable;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer iId;
	private String strName;
	private String strPwd;
	private String strGender;
	
	public Person() {
		
	}
	
	public Person(String name, String password, String gender) {
		this.strName	= name;
		this.strPwd 	= password;
		this.strGender  = gender;
	}
	
	public Integer getId() {
		return this.iId;
	}

	public String getName() {
		return this.strName;
	}
	
	public String getPassword() {
		return this.strPwd;
	}
	
	public String getGender() {
		return this.strGender;
	}
	
	public void setId( Integer id) {
		this.iId = id;
	}
	
	public void setName(String name) {
		this.strName = name;
	}

	public void setPassword(String password) {
		this.strPwd= password;
	}

	public void setGender(String gender) {
		this.strGender= gender;
	}


}