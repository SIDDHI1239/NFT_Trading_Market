package edu.sjsu.cmpe275.nft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "CRYPTOCURRENCY_WALLET")
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "WALLET_ID")
	private int id;
	
//	@Column(name = "CURRENCY_TYPE")
//	private String currencyType;
	
	@Column(name = "BALANCE")
	private float balance;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "CURRENCY_TYPE")
	private Cryptocurrency currency;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getCurrencyType() {
//		return currencyType;
//	}
//
//	public void setCurrencyType(String currencyType) {
//		this.currencyType = currencyType;
//	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cryptocurrency getCurrency() {
		return currency;
	}

	public void setCurrency(Cryptocurrency currency) {
		this.currency = currency;
	}

}