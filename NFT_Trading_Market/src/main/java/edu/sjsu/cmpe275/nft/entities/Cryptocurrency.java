package edu.sjsu.cmpe275.nft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CRYPTOCURRENCY")
public class Cryptocurrency {

	@Id
	@Column(name = "CURRENCY_TYPE")
	private String currencyType;
	
	@Column(name = "NAME")
	private String name;

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

}
