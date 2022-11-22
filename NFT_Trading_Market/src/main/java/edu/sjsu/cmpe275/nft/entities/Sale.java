package edu.sjsu.cmpe275.nft.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "SALE")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SALE_ID")
	private int id;
	
	@Column(name = "TYPE")
	@Enumerated(EnumType.ORDINAL)
	private SalesType type;
	
	@Column(name = "EXPECTED_VALUE")
	private double expectedValue;
	
	@Column(name = "RECEIVED_VALUE")
	private double receivedValue;
	
	@Column(name = "CREATION_TIME")
	private Timestamp creationTime;
	
	@Column(name = "CLOSING_TIME")
	private Timestamp closingTime;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User seller;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User buyer;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TOKEN_ID")
	private NFT nft;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "SYMBOL")
	private Cryptocurrency cryptocurrency;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SalesType getType() {
		return type;
	}

	public void setType(SalesType type) {
		this.type = type;
	}

	public float getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(float expectedValue) {
		this.expectedValue = expectedValue;
	}

	public float getReceivedValue() {
		return receivedValue;
	}

	public void setReceivedValue(float receivedValue) {
		this.receivedValue = receivedValue;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Timestamp closingTime) {
		this.closingTime = closingTime;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	
	public NFT getNft() {
		return nft;
	}

	public void setNft(NFT nft) {
		this.nft = nft;
	}

	public Cryptocurrency getCryptocurrency() {
		return cryptocurrency;
	}

	public void setCryptocurrency(Cryptocurrency cryptocurrency) {
		this.cryptocurrency = cryptocurrency;
	}

}