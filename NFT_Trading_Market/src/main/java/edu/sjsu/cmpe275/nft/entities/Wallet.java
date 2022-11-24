package edu.sjsu.cmpe275.nft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CRYPTOCURRENCY_WALLET")
@IdClass(WalletId.class)
public class Wallet {
	
	@Column(name = "BALANCE")
	private double balance;
	
	@Id
	@OneToOne
	@JoinColumn(name = "WALLET_ID", referencedColumnName = "USER_ID")
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "SYMBOL")
	private Cryptocurrency cryptocurrency;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cryptocurrency getCryptocurrency() {
		return cryptocurrency;
	}

	public void setCryptocurrency(Cryptocurrency cryptocurrency) {
		this.cryptocurrency = cryptocurrency;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}