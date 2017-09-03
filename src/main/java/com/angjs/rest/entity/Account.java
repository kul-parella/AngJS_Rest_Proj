package com.angjs.rest.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="account")
//@XmlType(propOrder = { "actId", "holderName", "ssn", "actStatus","balance"})
public class Account {
	
	private int actId;
	private String holderName;
	private String ssn;
	private String actStatus;
	private String balance;
	
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public int getActId() {
		return actId;
	}
	public void setActId(int actId) {
		this.actId = actId;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getDob() {
		return ssn;
	}
	public void setDob(String dob) {
		this.ssn = dob;
	}
	public String getActStatus() {
		return actStatus;
	}
	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

}
