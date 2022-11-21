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
	@Column(name = "CURRENCY_TYPE")
	private String currencyType;
	
	@Column(name = "NAME")
	private String name;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "CURRENCY_TYPE")
	private Sale sale;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "currency")
	private List<Wallet> wallets;

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public List<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(List<Wallet> wallets) {
		this.wallets = wallets;
	}

}
