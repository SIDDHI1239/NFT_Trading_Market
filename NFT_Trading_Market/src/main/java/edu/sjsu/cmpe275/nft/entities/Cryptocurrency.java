package edu.sjsu.cmpe275.nft.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "CRYPTOCURRENCY")
public class Cryptocurrency {

	@Id
	@Column(name = "SYMBOL")
	private String symbol;
	
	@Column(name = "NAME")
	private String name;
	
	@JsonBackReference
	@OneToMany(mappedBy = "cryptocurrency")
	private List<Sale> sales;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cryptocurrency")
	private List<Wallet> wallets;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Sale> getSale() {
		return sales;
	}

	public void setSale(List<Sale> sales) {
		this.sales = sales;
	}

	public List<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(List<Wallet> wallets) {
		this.wallets = wallets;
	}

}