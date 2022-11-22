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
@IdClass(WalletId.class)
public class Wallet {

	@Column(name = "BALANCE")
	private float balance;
	
	@ManyToOne
	@JoinColumn(name = "WALLET_ID", referencedColumnName= "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "SYMBOL")
	private Cryptocurrency currency;

	@Column(name = "BALANCE")
	private double balance;

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

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

}