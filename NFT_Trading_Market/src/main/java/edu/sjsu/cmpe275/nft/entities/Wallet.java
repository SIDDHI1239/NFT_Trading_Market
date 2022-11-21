package edu.sjsu.cmpe275.nft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CRYPTOCURRENCY_WALLET")
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "WALLET_ID")
	private int id;
	
	@Column(name = "CURRENCY_TYPE")
	private String currencyType;
	
	@Column(name = "BALANCE")
	private float balance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

}
